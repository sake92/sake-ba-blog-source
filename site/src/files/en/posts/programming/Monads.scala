package files.en.posts.programming

import java.time.LocalDate
import scalatags.Text.all._
import utils.Imports._
import ba.sake.hepek.html.statik.Section

object Monads extends ProgrammingTemplate {

  override def pageSettings =
    super.pageSettings
      .withTitle("You already know Monad(ic) stuff")
      .withLabel("Monadic stuff")
      .withDescription(
        "Monads introduction. Do notation, for comprehensions. Comparing monads with async and await syntax. Future, Observalbe, IO monad, Reactive programming."
      )

  override def blogSettings =
    super.blogSettings
      .withSections(
        introSection,
        haskellSection,
        historySection,
        monadsSection,
        asyncAwaitSection,
        opinionsSection,
        additionalResourcesSection
      )

  def introSection = Section(
    "Intro",
    s"""
    Yes, yet another Monad tutorial, why not?

    If you wonder what
    - flatMap
    - Monads
    - Promise, Future, Rx, IO
    - do, for, async/await

    have in common, then you are in a good place.

    First we'll see how and why `Monad` came into Haskell (a *purely* functional language).  
    Then we will see a nicer syntax for writing functions that operate on monads.  
    And lastly I will show you some similarities between Monad and async/await.
    """.md
  )

  def haskellSection = Section(
    "Haskell",
    s"""
    A few relevant things about Haskell:
    - it is a lazy language
    - it wants to separate **pure functions** from **impure functions (actions)**

    ### Laziness
    By "lazy" we mean non-strict evaluation.  
    In Java for example, when you call a function `myFun(a, b)`, the order of evaluation is strict and consistent:
    - arguments are evaluated from left to right, one after another
    - function is evaluated

    But in Haskell that's not the case. *Nothing is evaluated until needed*.  
    So if the parameter `a` is not used in the body of `myFun` **it will not be evaluated at all**.  
    This is fine+desirable+performant when your functions are pure (not *doing* anything),  
    but it is a big issue when they do **side effects**: write to a file/db etc.  
    For example, if you want these actions to be executed:
    - `f1`: write to a file
    - `f2`: read from that file

    you need to make sure that `f1` always gets evaluated before `f2`!!  
    In Haskell you are never sure because eval order is unspecified..

    > The next Haskell will be strict.  
    > ~ Simon Peyton Jones

    ### Pure functions
    Pure functions are like **mathematical functions**, they do calculations, and only return new values (no mutable variables).  
    They are only considering "insides" of a program, its own memory.  

    ### Impure functions
    Impure functions go *beyond our program*, they "go outside", play and get dirty.  
    They read/write to a file/console/database etc.

    So, Haskell wants you *not to get dirty*, and play as much as you can *inside* (stay safe).  
    But how does it know which functions are "impure"?  
    Usually by marking them with `IO` wrapper type (which is "a monad").

    ### Main function
    "Normal" programming languages have a `main` function, which usually looks something like this:
    ```java
    static void main(String[] args) {
    }
    ```
    but in Haskell you have this:
    ```haskell
    main :: IO ()
    main = ...
    ```
    Haskell marks the main as an IO action, so by definition it is impure.
    """.md
  )

  def historySection = Section(
    "History and pre-history",
    s"""
    Before monads were introduced, main function's type looked like this:  
      `main :: [Response] -> [Request]`.  
    Example taken from [SO](https://stackoverflow.com/a/17004448/4496364):
    ```haskell
    main :: [Response] -> [Request]
    main responses =
      [
        AppendChan "stdout" "Please enter a Number",
        ReadChan "stdin",
        AppendChan "stdout" . show $$ enteredNumber * 2
      ]
      where (Str input) = responses !! 1
            firstLine = head . lines $$ input
            enteredNumber = read firstLine 
    ```
    In a nutshell, you had to **write all of the impure stuff that your whole program will do as a return value**.  
    That is represented as a *list of requests*: `[Request]`.  
    Return values from those IO actions are delivered in the `[Response]` list, that you would use inside the program logic.  

    The *number of responses* is the same as the *number of requests* you gave.  
    So you have to keep in mind the indices and bounds of responses, which is a bummer. 
    What if you add one request in the middle? You'd have to change all indices after it...  
    Which request belongs to which response? That's really hard to see.  
    We can already see that this way of writing a program is very **cumbersome, unreadable, and limited**.

    Notice also that the approach above works only because Haskell is lazy!
    """.md
  )

  def monadsSection = Section(
    "Monads",
    s"""
    Back to `IO t`. The `IO t` is an action that will do some *side effects* before returning a value of type `t`.  
    This can be *anything*: writing to disk, sending HTTP requests etc.  
    We have these impure functions in Haskell:
    ```haskell
    getChar :: IO Char
    putChar :: Char -> IO ()
    ```

    We already know some special *functions* that operate on the values *inside* the `IO`!  
    For example, we have `fmap :: Functor f => (a -> b) -> f a -> f b` which transformes the value inside any Functor `f`.  
    
    But what about chaining, sequencing actions one after another?  
    How can we ensure that `getChar` executes *strictly before* `putChar`?  
    Monads to the rescue! Its core function is called `bind` (usually called `flatMap` in other languages):
    ```haskell
    (>>=) :: IO a -> (a -> IO b) -> IO b
    ```
    FlatMap:
    - takes an `IO a` action
    - takes a function that takes `a` (from the `IO a` argument) and returns `IO b`
    - returns a new `IO b`

    So there we have it, Monad in all its glory! :)  
    Let's see our solution:
    ```haskell
    echo = getChar >>= putChar

    -- or more verbosely
    echo = getChar >>= (\\c -> putChar c)

    -- or verbosiest
    echo = (>>=) getChar (\\c -> putChar c)
    ```

    In Scala you'd write `val echo = getChar.flatMap(putChar)`.

    This is the main reason why Monads were introduced in Haskell.  
    > In short, Haskell is the world's finest imperative programming language.  
    > ~ Simon Peyton Jones

    ### Syntax sugar for Monads
    Haskell and some other languages have built in syntax support for Monads.  
    Haskell has "do notation" and Scala has "for comprehensions".  
    It makes them more readable by flipping sides:
    ```haskell
    echo = do
        c <- getChar
        putChar c
    ```

    Scala:
    ```scala
    val echo = for {
        c <- getChar
        _ <- putChar(c)
    } yield ()
    ```
    
    The `<-` symbol gets translated into `>>=` by Haskell's compiler.  
    In case of Scala, it gets turned into a `flatMap`.

    ---
    It turnes out that Monads are useful not only in the `IO` context, but for other types too.  
    Whenever you have unwanted `Wrapper[Wrapper[T]]` wrappers, you need to "flatMap that shit" => Monads.  
    If you have `List[List[String]]` you probably needed a `flatMap` instead of `map`.  
    If you have `Option[Option[String]]` => same thing.  
    
    You can imagine doing the same example above with a `List[T]`, where `c` would be *one element of the list*.
    """.md
  )

  def asyncAwaitSection = Section(
    "Async / Await",
    s"""
    After some time it came to my mind that we are doing a similar thing in JS/C#/Kotlin with `await`.
    It is almost the same thing, we are "pulling a value from a Promise/Task":
    ```js
    async function fetchUserMovies() {
      const user = await fetch('/user');
      const movies = await fetch(`/user/$${user.id}/movies`);
      console.log(movies);
      return movies;
    }
    ```
    Before this we used to write "normal functions":
    ```js
    function fetchUserMovies() {
        fetch('/user').then(user => {
            fetch(`/user/$${user.id}/movies`).then(movies => {
                console.log(movies);
                return movies;
            });
        });
    }
    ```
    Seems like `then` corresponds to `flatMap`, and `await` corresponds to `<-` in do syntax.  
    Some noticable differences:
    - do/for is general, while await is specific just for Promise
    - do/for in statically typed languages is checked for proper types, while in JS you're on your own
    """.md
  )


  def opinionsSection = Section(
    "My opinions",
    """
    To me, it feels very awkward to program in a lazy programming language.  
    It is hard to reason about and you *have to use monads* for doing even the simplest IO operations.  
    Seems like it introduces more problems than it gives us benefits.

    So, in my opinion, use Monads/Rx/whatever *only when you have to*!  
    The simpler the program - the better.  

    For example, in Java you can use threads and concurrent datastructures. 
    Web servers like Tomcat, Jetty etc. are working just fine with a thread-per-request model.

    But in JS you don't have that liberty, you **need to use Promises**.  
    That's because JS doesn't have "normal threads", is uses an event-loop so you have to program asynchronous code.

    ---
    I hope this gave you a better insight into scary Monads and the FP way of handling IO.
    """.md
  )

  def additionalResourcesSection = Section(
    "Additional resources",
    """
    - [Tackling the Awkward Squad](https://www.microsoft.com/en-us/research/wp-content/uploads/2016/07/mark.pdf?from=https%3A%2F%2Fresearch.microsoft.com%2F%7Esimonpj%2Fpapers%2Fmarktoberdorf%2Fmark.pdf) by Simon Peyton Jones
    - [Essential Effects](https://www.youtube.com/watch?v=9TR--8gAcZ8) by Adam Rosien
    - [State of Loom](https://cr.openjdk.java.net/~rpressler/loom/loom/sol1_part1.html) by Ron Pressler (comparing RX vs threads)
    - [Future vs IO](https://gist.github.com/dcastro/c451883ff8aac44c57233ef1c6fd75ee) by Diogo Castro
    - [The Observable disguised as an IO Monad](https://medium.com/@luijar/the-observable-disguised-as-an-io-monad-c89042aa8f31) by Luis Atencio
    - [Benefits of IO discussion on Reddit](https://www.reddit.com/r/scala/comments/8ygjcq/can_someone_explain_to_me_the_benefits_of_io/)
    """.md
  )

}
