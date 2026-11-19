---
title: Running a Scala Web App on AWS Lambda
description: An ordinary Scala HTTP app on AWS Lambda, using a GraalVM native image, the Lambda Web Adapter and OpenTofu.
publish_date: 2026-11-03 13:00
---

# Running a Scala Web App on AWS Lambda

In the [previous post](/en/programming/sharaf-deployment.html), I wrote about deploying a small web app on a VPS with Caddy and VoidAuth.  
But what if you don't want to maintain a server at all?

I've put together a [Sharaf AWS Lambda starter](https://github.com/sake92/sharaf-aws-lambda-starter) for that too.  
And I've tested the deployment on **AWS**, so this one has also seen the outside world :)

The setup is fairly small:

- **Sharaf** handles HTTP and app logic
- **GraalVM Native Image** compiles the app into a native executable for fast startup
- **Lambda Web Adapter** connects the HTTP server to Lambda
- **OpenTofu** creates the AWS infrastructure

The nice bit is that the app is still an ordinary HTTP server!

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

There is no Lambda-specific handler in the app. It has a health endpoint and a small greeting endpoint, with the usual JSON body and query parameter parsing.

And, just like the Caddy setup, **this doesn't require Sharaf**.  
The adapter works with other HTTP servers too.

## GraalVM native image

The starter uses Sharaf with its experimental Potateh adapter. Docker builds a GraalVM native image, producing a statically linked musl executable. The final Alpine image runs that executable directly, without a JVM.

Startup matters for Lambda because a request can arrive when there is no running instance ready to handle it. AWS then needs to start a new execution environment, including your app. That's the cold start people talk about.

The native executable skips JVM startup. I haven't measured the full AWS Lambda cold start for this starter yet.

## Infrastructure with OpenTofu

[The infrastructure definition](https://github.com/sake92/sharaf-aws-lambda-starter/blob/main/terraform/main.tf) creates an ECR repository for the Docker image, a Lambda function, an execution role, a CloudWatch log group, and a Function URL.

The Function URL gives you an HTTPS endpoint directly, so this starter doesn't need API Gateway or a separate reverse proxy.

The first deployment happens in two parts. OpenTofu creates the image repository and supporting resources, then the scripts build and push the image. A second apply creates the Lambda function using that image's digest.

Using the digest pins the deployment to a specific image. You know exactly which build the function runs.

The demo URL is **public and unauthenticated**. Anyone can call it, and those calls can incur charges.

## Testing without an AWS account

The starter also has an integration test using [Floci](https://github.com/floci-io/floci), a local AWS emulator, through Testcontainers.

It builds the same Docker image used for deployment and calls it through an emulated Lambda Function URL.  
That exercises the adapter too: JSON bodies, query parameters, validation errors and unknown routes all travel through the full request path.

You need Docker, but no AWS account or credentials for that test.  
There are also route tests for the app itself that run on the JVM.

An emulator doesn't prove every detail of AWS behavior, but it's useful to check the app and adapter together before deploying.

## What about DynamoDB?

The starter doesn't have a database yet. For a Lambda app, I'd look at storing persistent data outside the function, for example in DynamoDB.

One thing I'd like to try is [Smithy4s](https://disneystreaming.github.io/smithy4s/docs/protocols/aws/aws/). It can generate Scala clients from AWS service models, and its documentation includes a DynamoDB example.

**I haven't tried this integration yet**, so DynamoDB access, credentials and compatibility with this native build still need checking. It's an idea for the next experiment :)

## Give it a try

The [repository README](https://github.com/sake92/sharaf-aws-lambda-starter#readme) has the local run, test and AWS deployment instructions, including how to remove the resources afterwards.

If you want to keep writing a normal Scala web app and let AWS handle the servers, this is a small starting point.  
Same HTTP routes, different place to run them :)
