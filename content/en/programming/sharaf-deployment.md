---
title: Easy Deployment with Sharaf, Caddy and VoidAuth
description: A small web app deployment setup with automatic HTTPS, authentication, a GraalVM native image and SQLite. Tested on Hetzner!
publish_date: 2026-11-03 12:00
---

# Easy Deployment with Sharaf, Caddy and VoidAuth

You build a nice web app, it works locally, and now you want to put it on a server.  
Then comes HTTPS, user registration, login, sessions, forgot-password emails..  
Quite a bit of work before you even get to the actual app!

I've put together a [Sharaf deployment starter](https://github.com/sake92/sharaf-deployment-starter) that takes care of most of this.  
And I've tested it on **Hetzner**, so it has seen an actual VPS too :)

The setup is fairly small:

- **Caddy** handles HTTPS and reverse proxying
- **VoidAuth** handles authentication and user management
- **Sharaf** handles plain HTTP and app stuff :)

Everything runs with Docker Compose, and the persistent state lives in SQLite and files on disk.

## HTTPS with Caddy

Setting up SSL certificates can be annoying. Getting one, configuring the server, remembering renewals..

[Caddy handles this automatically](https://caddyserver.com/docs/automatic-https).  
Point your domain at the VPS, allow ports 80 and 443 through the firewall, and configure the hostname.  
Caddy obtains a free TLS certificate, renews it, and redirects HTTP to HTTPS.

The Sharaf app just listens on plain HTTP inside the Docker network.  
Only Caddy exposes the public HTTP/HTTPS ports.

## Authentication without the OAuth2 dance

Auth is usually where things get more involved.

Even a simple username/password login means maintaining users, hashing passwords, sessions, registration, email verification, password resets etc.  
And if you go with an external identity provider, there is the OAuth2/OIDC dance to wire into your app.

[VoidAuth](https://github.com/voidauth/voidauth) handles the login and account management part.  
The nice bit is that the app can use **just HTTP headers** to find out who the user is!

For a protected request, Caddy asks VoidAuth whether the user is allowed through.  
If they need to log in, they get redirected to the login page.  
Once authenticated, Caddy forwards the request to Sharaf with identity headers such as:

```text
Remote-User: alice
Remote-Name: Alice
Remote-Email: alice@example.com
Remote-Groups: users
```

This is called [ProxyAuth / trusted header SSO](https://github.com/voidauth/voidauth/blob/main/docs/ProxyAuth-and-Trusted-Header-SSO-Setup.md).  
VoidAuth owns the authentication session. Your app reads the headers and does its thing.  
**No need to implement OAuth2 callbacks, login sessions, registration or forgot-password flows in the app.**

Of course, those headers need to come from a trusted proxy!  
The starter keeps the app private and strips incoming identity headers before setting the verified ones. Otherwise anyone could send `Remote-User: admin`, which would be.. unfortunate.

App-specific permissions still belong in your app. Knowing who Alice is doesn't automatically mean she can edit Bob's documents.

## Sharaf does the app stuff

The starter uses [Sharaf](https://github.com/sake92/sharaf) with its experimental Potateh adapter. Docker builds a GraalVM native image and runs the resulting executable in Alpine.

It has a public homepage and assets, and a protected `/me` route that returns the user's identity.

VoidAuth handles the login flow, so the app only needs to read the identity headers from Caddy.

Also, **nothing about this requires Sharaf**.  
You could put another Scala HTTP server, a Go app, a Python app, or pretty much anything that speaks HTTP behind the same setup.

## SQLite and files

VoidAuth stores users and sessions in SQLite. Config, uploads and Caddy's certificate state live in mounted directories.  
No separate database server needed for this setup.

For SQLite backups, I've written about [simple daily snapshots to S3-compatible storage](/en/programming/sqlite-backups-s3.html), using a small script and a systemd timer. You can lose changes since the last successful snapshot, so choose based on how much data you can afford to lose.

For continuous backups, you can add [Litestream](https://litestream.io/), which streams database changes to backup storage.  
With frequent writes, Litestream can upload many tiny files and consume a lot of storage operations. Check request costs as well as storage space; its [sync interval](https://litestream.io/reference/config/) affects that tradeoff.

Neither backup option is configured in the starter yet. The other files, including the private `.env` with the storage key, need to be backed up too.

## Give it a try

The [repository README](https://github.com/sake92/sharaf-deployment-starter#readme) has local and VPS setup instructions.  
For a real deployment you'll need a domain, SMTP for verification/password-reset emails, and the initial VoidAuth admin and ProxyAuth domain setup.

I'd also like to have API keys and other auth methods covered by the same setup. For now, I'd handle those in the app, with separate proxy rules for those routes. Those are usually much smaller pieces than the whole browser login/account-management machinery.

For a small web app on a VPS, this gets quite a lot of annoying stuff out of the way.  
Then you can get back to building the app :)
