---
title: Simple SQLite Backups to S3-Compatible Storage
description: Daily SQLite snapshots to S3-compatible storage, with a small Bash script, systemd timer and seven-day retention.
publish_date: 2026-11-05 20:00
---

# Simple SQLite Backups to S3-Compatible Storage

SQLite makes running a small web app quite pleasant. One database file, no separate database server to maintain..  
But that file still needs a backup somewhere outside your VPS!

Here's a small setup for that: take a snapshot, check it, compress it, and upload it to a private S3-compatible bucket once a day. A systemd timer runs the job, and a bucket lifecycle rule removes old backups.

You can use [Amazon S3](https://docs.aws.amazon.com/cli/latest/userguide/cli-services-s3-commands.html), [Cloudflare R2](https://developers.cloudflare.com/r2/examples/aws/aws-cli/), [Backblaze B2](https://www.backblaze.com/docs/cloud-storage-use-the-aws-cli-with-backblaze-b2) or [DigitalOcean Spaces](https://docs.digitalocean.com/products/spaces/reference/s3-compatibility/). They all support uploads through the AWS CLI, with different endpoints, credentials and pricing.

It works with any app using SQLite. If your app runs in Docker, the script runs on the host and reads the database from its bind mount.

## What about Litestream?

[Litestream](https://litestream.io/) continuously replicates SQLite changes to backup storage. It's useful when you need to recover changes made since the last daily backup.

One thing to watch, though: **Litestream can consume a lot of storage operations because it uploads many tiny files**. With frequent database writes and a short sync interval, those small uploads can add up to a lot of PUT requests, even when the total data is small. The [Litestream configuration docs](https://litestream.io/reference/config/) explain this tradeoff. Increasing the sync interval reduces request frequency, but increases the window of changes you could lose.

Check your provider's operation allowances and pricing as well as storage space. [R2 charges for operations too](https://developers.cloudflare.com/r2/pricing/), so a small database doesn't automatically mean a small request bill.

If a daily snapshot is enough for your app, the setup below is fairly simple. **You can lose roughly a day's changes if the server dies just before the next backup**, and more if backups have been failing. Each run also uploads the whole compressed database. Pick the schedule based on how much data you can afford to lose.

## Prepare the VPS and bucket

You'll need Bash, SQLite's `sqlite3` CLI, gzip, GNU coreutils and AWS CLI v2 on a Linux VPS with systemd.

Install the AWS CLI using the [official instructions](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html). For a new system-wide installation:

```sh
curl -fsSL https://awscli.amazonaws.com/v2/install.sh -o /tmp/aws-cli-install.sh
sudo bash /tmp/aws-cli-install.sh --system
command -v aws
aws --version
```

The system-wide installation makes `aws` available in `/usr/local/bin`, so systemd can find it too.

Create a **private bucket** with your storage provider.

### Configure the retention period

Configure a lifecycle rule in your storage provider's bucket settings. Set the prefix to `my-app/db/` and expire backup objects **7 days** after upload. Use the same prefix for `S3_PREFIX` in the configuration below.

In R2, this is under **Settings → Object Lifecycle Rules → Add rule**. Other providers have their own lifecycle settings; check how expiration works there, especially if bucket versioning is enabled.

Without the rule, backups keep accumulating. Deletion can take time ([R2 handles it asynchronously](https://developers.cloudflare.com/r2/buckets/object-lifecycles/)), so allow space for delays as well as database growth.

At 500 MB per compressed snapshot, seven daily backups need about 3.5 GB, plus that headroom.

### Configure bucket access

Create credentials scoped to the bucket. Allow uploads and downloads, including multipart uploads for larger backups. Copy the S3 API endpoint and region from your provider's settings or docs.

| Provider | S3 API endpoint | Region |
| --- | --- | --- |
| Amazon S3, Frankfurt | `https://s3.eu-central-1.amazonaws.com` | `eu-central-1` |
| Cloudflare R2 | `https://ACCOUNT_ID.r2.cloudflarestorage.com` | `auto` |
| Backblaze B2 | `https://s3.REGION.backblazeb2.com` | Your bucket's region |
| DigitalOcean Spaces, New York | `https://nyc3.digitaloceanspaces.com` | `nyc3` |

The configuration below uses Amazon S3 in Frankfurt. Replace the endpoint and region with the values for your own bucket.

Create a private configuration file:

```sh
sudo touch /etc/sqlite-backup.env
sudo chmod 0600 /etc/sqlite-backup.env
sudo nano /etc/sqlite-backup.env
```

Paste this and replace the paths and credentials:

```ini
DB_PATH=/opt/my-app/data/app.db
S3_ENDPOINT_URL=https://s3.eu-central-1.amazonaws.com
S3_REGION=eu-central-1
S3_BUCKET=YOUR_PRIVATE_BACKUP_BUCKET
S3_PREFIX=my-app/db
AWS_ACCESS_KEY_ID=YOUR_STORAGE_ACCESS_KEY_ID
AWS_SECRET_ACCESS_KEY=YOUR_STORAGE_SECRET_ACCESS_KEY
```

`DB_PATH` is the path on the **host**, which may differ from the path inside your container. Keep enough temporary disk space for both the snapshot and its compressed copy.

## Take a snapshot and upload it

Use SQLite's [online backup](https://www.sqlite.org/backup.html) through the CLI's `.backup` command. The app can keep running, and committed changes in the WAL are included. Simply copying a live `.db` file can miss those changes or produce an inconsistent backup.

Create `/usr/local/bin/backup-db.sh`:

```sh
sudo nano /usr/local/bin/backup-db.sh
```

Paste the following, including the first line:

```bash
#!/usr/bin/env bash
set -euo pipefail
umask 077

: "${DB_PATH:?Set DB_PATH to the host SQLite database file}"
: "${S3_ENDPOINT_URL:?Set S3_ENDPOINT_URL to the storage S3 API endpoint}"
: "${S3_REGION:?Set S3_REGION to the storage region}"
: "${S3_BUCKET:?Set S3_BUCKET}"
: "${AWS_ACCESS_KEY_ID:?Set AWS_ACCESS_KEY_ID}"
: "${AWS_SECRET_ACCESS_KEY:?Set AWS_SECRET_ACCESS_KEY}"

for dependency in sqlite3 aws gzip timeout mktemp realpath date; do
  command -v "$dependency" >/dev/null || { echo "Missing command: $dependency" >&2; exit 1; }
done
if [[ ! -f "$DB_PATH" || ! -r "$DB_PATH" ]]; then
  echo "Database is missing or unreadable: $DB_PATH" >&2
  exit 1
fi
db_path=$(realpath -e -- "$DB_PATH")

work_dir=$(mktemp -d "${TMPDIR:-/tmp}/sqlite-backup.XXXXXXXXXX")
trap 'rm -rf -- "$work_dir"' EXIT
trap 'exit 130' INT
trap 'exit 143' TERM
cd "$work_dir"

echo "Creating database snapshot" >&2
timeout --kill-after=10s 5m sqlite3 -batch -bail -readonly "$db_path" \
  '.timeout 10000' '.backup snapshot.db'
check=$(timeout --kill-after=10s 5m sqlite3 -batch -bail -readonly snapshot.db 'PRAGMA integrity_check;')
if [[ "$check" != "ok" ]]; then
  echo "Snapshot failed SQLite integrity_check; upload cancelled" >&2
  exit 1
fi
timeout --kill-after=10s 5m gzip -n snapshot.db

prefix=${S3_PREFIX:-my-app/db}
prefix=${prefix#/}
prefix=${prefix%/}
object_name="sqlite-$(date -u +%Y%m%dT%H%M%SZ)-${work_dir##*.}.db.gz"
destination="s3://${S3_BUCKET}/${prefix:+${prefix}/}${object_name}"

export AWS_EC2_METADATA_DISABLED=true
export AWS_RETRY_MODE=standard
export AWS_MAX_ATTEMPTS=5
export AWS_PAGER=""
timeout --kill-after=10s 15m aws s3 cp snapshot.db.gz "$destination" \
  --endpoint-url "$S3_ENDPOINT_URL" --region "$S3_REGION" \
  --cli-connect-timeout 15 --cli-read-timeout 120 --only-show-errors
echo "Uploaded database snapshot: $destination" >&2
```

Make it executable:

```sh
sudo chmod 0755 /usr/local/bin/backup-db.sh
```

The script checks the snapshot before uploading, gives each backup a UTC timestamp and a random suffix, and removes temporary files on exit. A failed snapshot, integrity check, compression or upload makes the job fail. Adjust the timeouts for a larger database or slower connection.

Each run creates one backup object. Large uploads can use multipart requests, so that doesn't necessarily mean one storage operation per backup.

## Run it every day

Create `/etc/systemd/system/sqlite-backup.service`:

```ini
[Unit]
Description=Snapshot SQLite database to S3-compatible storage
Wants=network-online.target
After=network-online.target

[Service]
Type=oneshot
EnvironmentFile=/etc/sqlite-backup.env
ExecStart=/usr/local/bin/backup-db.sh
TimeoutStartSec=30min
TimeoutStopSec=20s
KillMode=control-group
UMask=0077
PrivateTmp=true
NoNewPrivileges=true
```

This service runs as root so it can read the host-mounted database. If you use a dedicated account, add `User=` and `Group=` and grant access to the database directory and its WAL/SHM files.

If `command -v aws` showed a custom installation path, add an `Environment="PATH=..."` line under `[Service]` containing that directory and the usual system directories. An interactive shell's PATH isn't necessarily systemd's PATH.

Create `/etc/systemd/system/sqlite-backup.timer`:

```ini
[Unit]
Description=Daily SQLite database backup

[Timer]
OnCalendar=*-*-* 03:00:00
Persistent=true

[Install]
WantedBy=timers.target
```

This runs daily at 03:00 in the server's timezone. `Persistent=true` triggers a catch-up run if a scheduled run was missed while the timer was inactive.

Reload systemd and run a backup manually first:

```sh
sudo chmod 0644 /etc/systemd/system/sqlite-backup.service /etc/systemd/system/sqlite-backup.timer
sudo systemctl daemon-reload
sudo systemctl start sqlite-backup.service
sudo journalctl -u sqlite-backup.service -n 30 --no-pager
```

The start command waits for the backup to finish, which can take a few minutes. Follow the progress from another terminal with `sudo journalctl -u sqlite-backup.service -f`.

Look for `Uploaded database snapshot: ...`, and check that the object exists in your bucket. Then enable the timer:

```sh
sudo systemctl enable --now sqlite-backup.timer
systemctl list-timers sqlite-backup.timer
```

Check failures with `systemctl status sqlite-backup.service` and the journal. Connect failed runs to your server monitoring so you hear about them before you need a backup..

## Check that you can restore it

A successful upload is only part of the job. Download a backup and check it too!

In a root shell, load the configuration and use the object name from the journal:

```sh
sudo -i
set -a
. /etc/sqlite-backup.env
set +a
umask 077
mkdir -p /root/restore-check
cd /root/restore-check
aws s3 cp "s3://${S3_BUCKET}/${S3_PREFIX}/OBJECT.db.gz" ./restore-check.db.gz \
  --endpoint-url "$S3_ENDPOINT_URL" --region "$S3_REGION"
gzip -dc restore-check.db.gz > restore-check.db
sqlite3 -readonly restore-check.db 'PRAGMA integrity_check;'
```

Expect `ok`, then inspect the tables and records your app needs. Keep the configuration file compatible with shell syntax too; quote values containing spaces or shell metacharacters.

For a real restore, stop the app and all other database clients first. Preserve the current database together with any `-wal` and `-shm` files. Put the checked snapshot at the configured database path with the app's ownership and permissions, and ensure no old WAL/SHM files remain at that path before restarting the app.

This covers one SQLite database. Uploads, configuration, secrets and any other databases need their own backups too.

If you need a simple HTTP server on a single VPS with HTTPS and authentication, see the [Caddy, Sharaf and VoidAuth post](/en/programming/sharaf-deployment.html).
