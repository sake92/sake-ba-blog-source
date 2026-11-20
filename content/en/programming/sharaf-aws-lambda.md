---
title: Running a Scala HTTP API on AWS Lambda
description: Run a Scala HTTP API on AWS Lambda with GraalVM, the Lambda Web Adapter, DynamoDB and OpenTofu, and try the deployment locally with Floci.
publish_date: 2026-11-03 13:00
---

# Running a Scala HTTP API on AWS Lambda

In the [previous post](/en/programming/sharaf-deployment.html), I wrote about deploying a small web app on a VPS with Caddy and VoidAuth.  
But what if you don't want to maintain a server at all?

A common approach is to use serverless functions, such as AWS Lambda. But writing functions around Lambda's event types and APIs can feel cumbersome, especially when you already have a web app. I'd rather write ordinary HTTP routes and let something else handle the Lambda-specific plumbing.

When working with AWS infrastructure, I always have that feeling: "Will this work when I deploy it?" An OpenTofu or Terraform plan helps by showing what will change before you apply it. And being able to deploy locally with Floci gives me even more confidence that the pieces work together.

To try this out, I put together a [Sharaf AWS Lambda starter](https://github.com/sake92/sharaf-aws-lambda-starter) that packages an ordinary Scala HTTP server for Lambda and stores greetings in DynamoDB. You can try the whole deployment locally with Floci.

## An HTTP server inside Lambda

Usually, writing a Lambda function means implementing a handler that receives an event and returns a response.  
For an existing web app, that means adapting your routes to Lambda's event format.

The [AWS Lambda Web Adapter](https://github.com/aws/aws-lambda-web-adapter) takes care of this part.  
It receives Lambda invocations, turns them into HTTP requests to your server, and converts the HTTP responses back into Lambda responses.

The request path looks like this:

```text
HTTP client
    → Lambda Function URL (HTTPS)
    → Lambda Web Adapter
    → HTTP server
```

The adapter and the app are packaged in the same Docker image.  
Sharaf listens on port 8080 inside the Lambda environment, and the adapter forwards requests to it.

The adapter waits for `GET /health` before forwarding requests. The demo stores a greeting with `POST /greetings` and returns a `Location` header you can follow to read it back. The route code uses Sharaf's regular HTTP API.

## GraalVM native image

Docker builds a GraalVM native image, producing a statically linked Linux executable. The final Alpine image runs that executable directly, without a JVM.

Startup matters for Lambda because a request can arrive when there is no running instance ready to handle it. AWS then needs to start a new execution environment, including your app. That's the cold start people talk about.

The native executable skips JVM startup. I haven't measured the full AWS Lambda cold start for this starter yet.

## Infrastructure with OpenTofu

[The infrastructure definition](https://github.com/sake92/sharaf-aws-lambda-starter/blob/main/terraform/main.tf) creates an ECR repository, a Lambda function and Function URL, a CloudWatch log group, and the DynamoDB table. The Lambda execution role can write logs and read and write items in the greetings table.

The Function URL gives you an HTTPS endpoint directly, so this starter doesn't need API Gateway or a separate reverse proxy.

The first apply creates the repository and supporting resources. Once the scripts build and push the image to ECR, a second apply deploys the Lambda function and its URL. OpenTofu uses the image digest to pin the function to that build.

The demo URL is **public and unauthenticated**. Anyone can call it, and those calls can incur charges.

## Deploying locally with Floci

Docker Compose starts [Floci](https://github.com/floci-io/floci), a local AWS emulator, and its web console. The `floci` OpenTofu workspace points the same infrastructure definition at the emulator. After the first apply, `scripts/deploy-local.sh` builds and pushes the image to Floci's registry, applies the function configuration, and checks its Function URL. You can browse the resources at `http://localhost:4500`.

For an automated check, run `sbt integrationTest/testFull` with Docker running. Testcontainers starts Floci and the suite exercises the deployed image through an emulated Function URL, including a DynamoDB write and read. This runs without AWS credentials. An AWS deployment test is still pending.

The [starter README](https://github.com/sake92/sharaf-aws-lambda-starter#readme) has the local and AWS deployment commands, sample requests, and cleanup steps.
