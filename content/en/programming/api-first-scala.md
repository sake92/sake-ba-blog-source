---
title: API First Scala
description: API First Scala
---

# API First Scala

There are many ways to build APIs:
- YOLO approach
- code-first approach
- API-first approach

### YOLO approach
In the "YOLO mode" you don't care much about the users of your API.   
Maybe you have a few examples or docs you made, but nothing too serious.


### Code-first approach
In the "code-first" approach you start with the coding right away.
Schedules are tight, deadlines are near, pressure is real.

The specification (if any) is mostly an afterthought.
Consider Spring annotations, you can always sprinkle some `@ApiParam` here and there in your controllers.

There are also approaches like [Tapir](https://tapir.softwaremill.com/en/latest/), 
where you use a Scala DSL to define your API.
Then you implement the server logic, and generate the spec from that Scala code.
Potentially the client(s) too.

This approach is popular, since you start with coding right away.
But it has some downsides:
- you kinda play russian roulette with the spec, since it is generated from the code, so you can't really call it stable
- it is more code to maintain, increasing your cognitive load and compile times
- bundle size is increased, since you have to include the dependencies
- sometimes you have to fight the DSL to get what you want (e.g polymorphic payloads and such)
- you are limited by what DSL provides, if you need some framework-specific feature, it's challenging to get to it (or impossible)
    
    
### API-first approach
In the "API-first" approach you start with the API specification.
You define the API usually with OpenAPI (Swagger) in a YAML or JSON file.
Then you generate the server from that spec (and client code if need be).

This approach has some advantages:
- you are in full control of the spec, it is *the source of truth*
- spec is *stable* and *versioned*
- less code to maintain, there is no DSLs or annotations that obfuscate your code
- you can leverage your framework's features to the max

An example in Scala is the [Guardrail](https://guardrail.dev) tool.
It generates some server boilerplate code to the `target/.../src_managed/main` folder (you don't commit this to git).
Then you implement the server logic by *overriding those abstract definitions*, fill in the blanks essentially.
Note that when you change the `openapi.yaml`, the `src_managed` code is overwritten.
Guardrail is a nice approach, sadly it doesn't support Scala 3 yet.

A new kid on the block is the [OpenApi4s](https://github.com/sake92/openapi4s) tool that I made.
It takes a bit different approach, it doesn't hide the server code from you, it generates it *directly in your `src` folder*.
Exactly like you would have written it by hand.  
So how does it handle changes in the spec??? Overwrite the code every time? No, that would be silly, so let's see...

## OpenApi4s

TLDR: [OpenApi4s](https://github.com/sake92/openapi4s) refactors your code automatically, by using [regenesca](https://github.com/sake92/regenesca) diff+merge library.

Generating the models and controllers is the easy part:
- parse the OpenAPI spec
- generate models
- generate controllers

and that's it.

---

The hard part is *how to handle changes in the spec*.

Consider the classical [PetStore spec](https://petstore3.swagger.io).
OpenApi4s will generate something like this for the `User` model:
```scala
case class User(
    id: Option[Long],
    username: Option[String],
    firstName: Option[String],
    lastName: Option[String],
    email: Option[String],
    password: Option[String],
    phone: Option[String],
    userStatus: Option[Int]
) derives JsonRW
```

### Adding a new property
When you add a new `age` field of type `integer` to the `User` model.
OpenApi4s will compare the newly generated `case class User` (in-memory), with the existing `case class User` in your source code.
Then it will figure out that `age: Int` parameter is missing, and it will add it.

```diff
--- a/api/src/com/example/petstore/api/models/User.scala
+++ b/api/src/com/example/petstore/api/models/User.scala
@@ -14,5 +14,5 @@ case class User(
    email: Option[String],
    password: Option[String],
    phone: Option[String],
-  userStatus: Option[Int]
+  userStatus: Option[Int], age: Long
    ) derives JsonRW
```

### Changing a property
Let's say you change the `userStatus`'s format from `int32` to `int64`.
OpenApi4s will figure out that `userStatus: Option[Int]` needs to be changed to `userStatus: Option[Long]`.

```diff
--- a/api/src/com/example/petstore/api/models/User.scala
+++ b/api/src/com/example/petstore/api/models/User.scala
@@ -14,5 +14,6 @@ case class User(
    email: Option[String],
    password: Option[String],
    phone: Option[String],
-  userStatus: Option[Int]
+  userStatus: Option[Long]
```

### Adding a new endpoint
Adding a new endpoint to existing controller is easy too.
It will just add another `case` to your existing routes.
For example in sharaf framework it will add something like this:
```scala
case GET -> Path("user", "new-endpoint") =>
    Response.withStatus(StatusCodes.NOT_IMPLEMENTED)
```

It will even generate a boilerplate implementation for you.
You can then fill in the blanks.

### Changing an endpoint
Now this is a bit more tricky.
OpenApi4s must not touch your existing code, since you might have already implemented some logic.
So it will not touch your existing expressions, for example `Response.withStatus(..)` in the previous example.

But it will update the query parameters if needed.



## CI

### Preventing accidental overwrites
You might be cautious about the changes that OpenApi4s makes.
Thinking, will it overwrite my code? How can I make sure it doesn't?
A simple check you can do in your CI pipeline is to:
- touch the `openapi.yaml` file, just so that `mill` detects a change and triggers openapi4s
- compile the code, to regenerate the files
- see if there are any changes in the git diff

Example:
```sh
echo " " >> api/resources/openapi.yaml
./mill api.compile
truncate -s -1 api/resources/openapi.yaml
git diff --exit-code
[ $$? -eq 0 ]  || exit 1
```

### Preventing breaking changes
Since the `openapi.yaml` is now in `git`, you can do some cool stuff with it.
One very useful check is preventing breaking changes.
You can do it with [openapi-diff](https://github.com/OpenAPITools/openapi-diff) for example:

```sh
git show origin/main:api/resources/public/openapi.json > main_openapi.json

cs launch org.openapitools.openapidiff:openapi-diff-cli:2.1.0-beta.12 -M org.openapitools.openapidiff.cli.Main -- --fail-on-incompatible main_openapi.json ./api/resources/public/openapi.json
[ $$? -eq 0 ]  || exit 1

rm main_openapi.json
```

See the [CI script](https://github.com/sake92/openapi4s-demo/blob/main/.github/workflows/ci.yml) in the openapi4s demo repo.

Here is an example of [compatible change PR](https://github.com/sake92/openapi4s-demo/pull/2).  
And an example of [breaking change PR](https://github.com/sake92/openapi4s-demo/pull/3), CI fails of course.


## Conclusion

Hope you find this post (and OpenApi4s tool) useful.  
You can find more tools to combine with api-first approach at [https://openapi.tools/](https://openapi.tools/)
    
Check out the video demo on [YouTube](https://www.youtube.com/watch?v=kf0vGrlKNb8)!

### Additional resources
- [Understanding the API-First Approach](https://swagger.io/resources/articles/adopting-an-api-first-approach/)
- [Guardrail](https://guardrail.dev)
- [Tapir](https://tapir.softwaremill.com/en/latest/)
- [Documenting a Spring REST API Using OpenAPI 3.0](https://www.baeldung.com/spring-rest-openapi-documentation)



