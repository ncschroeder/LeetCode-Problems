# [LeetCode](https://leetcode.com/) Problems

My LeetCode username is csc156 and here's [my profile](https://leetcode.com/csc156/). As of January 17<sup>th</sup>, 2024; I solved 153 LeetCode problems. The LeetCode-designated difficulties of those problems are: 109 easy, 43 medium, and 1 "hard". My profile page says that I solved 2 hard problems but I cheated for 1 of them to see if LeetCode would be able to catch me and it didn't. The "hard" problem that I legitimately solved is ["Shortest Palindrome"](https://github.com/ncschroeder/LeetCode-Problems/blob/main/Shortest_Palindrome.md), which is included in this repo and you can visit that problem to see why I considered it "hard" and not hard.

For this repo, I decided to include 40 problems that involve a variety of concepts, difficulties, and tactics used. The designated difficulties of those problems are: 23 easy, 16 medium, and the aforementioned "hard" one. There are some concepts that I considered notable enough that I decided to list them out and the problems that involved that concept below, in the "Problem Lists" section.


There's a Markdown file for each problem and each file has the title, LeetCode-designated difficulty, LeetCode.com link to the problem, description, input and output examples, constraints, and code that solves the problem. There's often also some documentation about the solution, whether it be in markup or code comments. Sometimes I mention the time complexity.

There was some refactoring done on the code used for these problems. Most of it is what I considered to be minor so for most problems, there's 1 solution that includes the refactored code. There are some problems where I considered the refactoring to be significant enough that I decided to include original and refactored solutions.

## Favorite Challenges
Some problems have multiple solutions for other reasons. For example, ["Expressive Words"](https://github.com/ncschroeder/LeetCode-Problems/blob/main/Expressive_Words.md) and ["Find Peak Element"](https://github.com/ncschroeder/LeetCode-Problems/blob/main/Find_Peak_Element.md) have tail recursive and iterative solutions.

Every LeetCode problem has a "Discuss" section where people post solutions. For some problems, I posted my solution there. On [my profile page](https://leetcode.com/csc156/), there's a section that says "Recent AC", "Solutions", and "Discuss" and if you click on "Solutions", you can see the problems that I posted a solution for.

In the files in this repo, there are some mathematical expressions used that can be written with GitHub Markdown, such as $O(n^2)$ and $1^2 + 9^2 = 82$. Those 2 expressions were written using `$O(n^2)$` and `$1^2 + 9^2 = 82$`, respectively. More info about writing mathematical expressions on GitHub can be found in [this *GitHub Docs* article](https://docs.github.com/en/get-started/writing-on-github/working-with-advanced-formatting/writing-mathematical-expressions).

Some of my favorite challenges, in alphabetical order, are:
- Add Strings :heavy_plus_sign:
- Events Conflict :clock5:
- Find the Town Judge :man_judge:
- Find Tic Tac Toe Winner :x: :o:
- Game of Life
- Group Anagrams
- Jump Game 1 & 2
- Ransom Note
- Repeated Substring Pattern
- Reverse Vowels

## Hardest Challenges

The hardest challenge that I completed is Word Search. Other challenges that I consider the hardest (though not all of these are necessarily hard), in alphabetical order, are:
- Design Twitter
- Expressive Words
- Find Peak Element :mountain:
- Fizz Buzz (just kidding :smiley:)
- GCD of Strings
- House Robber
- Number of Islands :desert_island:
- Shortest Palindrome
- Zigzag Conversion
## Languages Used

### C++

There's 1 problem, ["Linked List Cycle"](https://github.com/ncschroeder/LeetCode-Problems/blob/main/Linked_List_Cycle.md), where I have a solution in C++. I decided to use C++ because I came up with a solution that uses memory addresses of objects, and those can be accessed with pointers :point_up_2: in C++ and cannot be accessed in Kotlin. I have some familiarity with C++ by using it for 2 courses in college. I also used C for 1 course.

### Kotlin

I used my favorite language, Kotlin, to solve the rest of the problems and implement a solution for "Linked List Cycle" that doesn't use pointers. Kotlin is statically typed and similar to C# and Java :coffee: in terms of features, though much of the syntax is different. I think Kotlin is more similar to C#; however, a notable similarity Kotlin has with Java is that Kotlin code can be compiled to Java bytecode! Kotlin code can also be compiled to native binaries or transpiled to front-end JavaScript. I first used Kotlin for an Android development course in college and then used it for my Singletonopoly project and then used it for LeetCode problems.


Here's a [Kotlin Docs article that gives a brief overview of syntax and some features of the language and has links to more detailed documentation articles](https://kotlinlang.org/docs/basic-syntax.html). You can also use the menu on the left edge of that page to access other articles. Here's the [Kotlin Standard Library documentation](https://kotlinlang.org/api/latest/jvm/stdlib/). If you go to the page for a class or function in the Standard Library, not only does that page describe the class or function but it also provides a link to the source code. Throughout the rest of the "Kotlin" section, including the collapsible section below, I'll give some links to pages in the Kotlin Docs and Standard Library documentation.

I like the features of Kotlin. Some of my favorite ones include [null safety](https://kotlinlang.org/docs/null-safety.html), [lambda features](https://kotlinlang.org/docs/lambdas.html#lambda-expressions-and-anonymous-functions) such as how `it` is the implicit param and the last expression is the return value, [extension functions](https://kotlinlang.org/docs/extensions.html#extension-functions), [destructuring](https://kotlinlang.org/docs/destructuring-declarations.html), and the ability to [name args when calling functions](https://kotlinlang.org/docs/functions.html#named-arguments). I also like the abundance of functions that the Standard Library provides. For example, if you go to [the `Iterable` page](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/), your scrollbar will get really short because of the amount of functions there are.


More info about Kotlin features used for these problems is in the collapsible section below.

<details>
<summary>More Info</summary>

#### Destructuring

This is often done for these problems. Any object can be configured to be able to be destructured and several built-in objects are configured this way. The objects that are destructured in these problems are [`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/)s, [`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/)s, [`Map.Entry`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/-entry/)s, [`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/)s, and [`IndexedValue`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-indexed-value/)s. More info about destructuring can be found [here](https://kotlinlang.org/docs/destructuring-declarations.html).

#### Infix Functions

These are occasionally used in these problems. Examples include [`until`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/until.html), [`to`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/to.html), and [`matches`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/matches.html). More info about infix functions can be found [here](https://kotlinlang.org/docs/functions.html#infix-notation).

#### Lambdas

These are often used in these problems. Info about lambda syntax and features can be found [here](https://kotlinlang.org/docs/lambdas.html#lambda-expressions-and-anonymous-functions).

#### Scope Functions

The functions [`let`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/let.html), [`run`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/run.html), [`also`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/also.html), [`apply`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/apply.html), and [`with`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/with.html) are known as *scope functions* and these are a special type of higher-order function. `let`, `also`, and `apply` are occasionally used in these problems. I used `let` to reduce variable usage and `also` and `apply` to easily create an object, immediately modify it, and store it to a variable. Examples are shown in the collapsible section below. More info about scope functions can be found [here](https://kotlinlang.org/docs/scope-functions.html).

<details>
<summary>Examples</summary>

##### `let`

The solution for ["Shortest Palindrome"](https://github.com/ncschroeder/LeetCode-Problems/blob/main/Shortest_Palindrome.md) looks like:

```kotlin
fun shortestPalindrome(s: String): String =
    if (s.isEmpty()) s
    else {
        (s.lastIndex downTo 0)
        .first { s.isPalindromeTo(it) }
        .let { s.substring(it + 1..s.lastIndex).reversed() + s }
    }

fun String.isPalindromeTo(lastIndex: Int): Boolean =
    ...
```

This is an alternative to:

```kotlin
fun shortestPalindrome(s: String) =
    if (s.isEmpty()) s
    else {
        val palindromeLastIndex: Int =
            (s.lastIndex downTo 0)
            .first { s.isPalindromeTo(it) }

        s.substring(palindromeLastIndex + 1..s.lastIndex).reversed() + s
    }
```

Another use of `let` is to run some code if a value isn't null after using the [safe call operator (`?.`)](https://kotlinlang.org/docs/null-safety.html#safe-calls). For example, part of the top-down solution for ["House Robber"](https://github.com/ncschroeder/LeetCode-Problems/blob/main/House_Robber.md) looks like:

```kotlin
fun rob(nums: IntArray): Int {
    ...
    val maxMoneys: Array<Int?> = ...

    fun getMaxMoney(index: Int): Int {
        // Return the memoized value if it exists.
        maxMoneys[index]?.let { return it }
        ...
    }
    ...
}
```

We can return from `getMaxMoney` inside the `let` since `let` is an [inline function](https://kotlinlang.org/docs/inline-functions.html).

##### `also`

In the bottom-up solution for ["House Robber"](https://github.com/ncschroeder/LeetCode-Problems/blob/main/House_Robber.md), this code is used:

```kotlin
val maxMoneys =
    IntArray(size = nums.size)
    .also {
        // Set last 3 values.
        it[it.lastIndex] = nums.last()
        it[it.lastIndex - 1] = nums[nums.lastIndex - 1]
        if (nums.size >= 3) {
            it[it.lastIndex - 2] = nums[nums.lastIndex - 2] + nums.last()
        }
    }
```

This is an alternative to:

```kotlin
val maxMoneys = IntArray(size = nums.size)
maxMoneys[maxMoneys.lastIndex] = nums.last()
...
```

##### `apply`

In ["Get Watched Videos by Your Friends"](https://github.com/ncschroeder/LeetCode-Problems/blob/main/Get_Watched_Videos_by_Your_Friends.md), this code is used:

```kotlin
val peopleToCheck =
    ArrayDeque<Pair<Int, Int>>()
    .apply { add(Pair(id, 0)) }
```

This is an alternative to:

```kotlin
val peopleToCheck = ArrayDeque<Pair<Int, Int>>()
peopleToCheck.add(Pair(id, 0))
```

</details>

#### Functions with Receivers

The `run`, `apply`, and `with` scope functions have a param named `block` and the type of it is `T.() -> Unit`, where `T` is a type param. The [`buildList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/build-list.html) and [`buildString`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/build-string.html) functions have a param named `builderAction` and the type of the `buildList` one is `MutableList<E>.() -> Unit`, where `E` is a type param, and the type of the `buildString` one is `StringBuilder.() -> Unit`. These types are functions with a receiver and the receiver type is the type before the `.()`. This type of function can be created with a lambda and inside that lambda, you can access members of the receiver type without needing an instance of that type. For example, a usage of `buildList` in ["Find Tic-Tac-Toe Winner"](https://github.com/ncschroeder/LeetCode-Problems/blob/main/Find_Tic-Tac-Toe_Winner.md) can be seen in the collapsible section below.

<details>
<summary>`buildList` Usage</summary>

```kotlin
fun tictactoe(moves: Array<IntArray>): String {
    val grid: List<MutableList<String?>> = ...
    ...
    // Let groups be a 2D list where each nested list contains 3 values for the contents of the rows, columns, and diagonal groups.
    val groups: List<List<String?>> =
        buildList(capacity = 8) {
            // Rows
            addAll(grid)

            // Columns
            for (col: Int in 0 until 3) {
                add((0 until 3).map { grid[it][col] })
            }

            // Diagonal groups
            add((0 until 3).map { grid[it][it] })
            add((0 until 3).map { grid[2 - it][it] })
        }
    ...
}
```

`add` and `addAll` are [`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/) functions that would otherwise be called like `someMutableList.add(...)` and `someMutableList.addAll(...)`.

</details>

`buildString` is used in ["Merge Strings Alternately"](https://github.com/ncschroeder/LeetCode-Problems/blob/main/Merge_Strings_Alternately.md).

More info about functions with receivers can be found [here](https://kotlinlang.org/docs/lambdas.html#function-types) and [here](https://kotlinlang.org/docs/lambdas.html#function-literals-with-receiver).

#### Eager and Lazy Collection Operations

Some collection operations, such as mapping and filtering, can be either eagerly or lazily evaluated. If one of those operations is performed on an [`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/), [`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/), [`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/), [`CharSequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/), or [`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/); it'll be eagerly evaluated and if performed on a [`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/), it'll be lazily evaluated. `Iterable` and `Sequence` are interfaces that have a type param `T` and require an implementation of `operator fun iterator(): Iterator<T>`. The same operations can be performed on iterables and sequences and many of those operations can also be performed on arrays, strings, char sequences, and maps. `Iterable` is the interface that types such as [`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/), [`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/), and [`IntRange`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/) implement. As implied a few sentences ago; arrays, strings, char sequences, and maps aren't iterables. An iterable, array, string, char sequence, or map can be converted to a sequence by using the [`asSequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/as-sequence.html) function, though there are a few other ways to create a sequence.

Here's [an article that mostly goes over sequences and lazy evaluation but also goes over iterables and eager evaluation](https://kotlinlang.org/docs/sequences.html).

A good question is: when should eager evaluation be used and when should lazy evaluation be used? There's not a clear answer to that. The 4<sup>th</sup> paragraph of the article linked above gives some info about that. Here's that paragraph:

> So, the sequences let you avoid building results of intermediate steps, therefore improving the performance of the whole collection processing chain. However, the lazy nature of sequences adds some overhead which may be significant when processing smaller collections or doing simpler computations. Hence, you should consider both `Sequence` and `Iterable` and decide which one is better for your case.

When a solution is submitted to LeetCode, lots of test cases with various input collection sizes will be ran with that solution. If we do multi-step processing on an input collection, whether it's better to use eager or lazy evaluation depends on the collection size and complexity of the operations, as implied in the article. Because of this, there doesn't seem to be a "right" choice when it comes to choosing between eager or lazy evaluation for LeetCode problems. Besides, these problems are for practice and/or entertainment anyway and not for software.

It's often more convenient to use eager evaluation since input collections are usually given to us in the form of arrays and strings, though I've done at least 1 problem where a list was an input collection. Because of that, we would have to use the `asSequence` function if we wanted to use sequences and lazy evaluation.

There are solutions to several problems in this repo where collection multi-step processing is done. For the above reasons, I decided to do that processing with eager evaluation and intermediate collections. Some examples are shown in the collapsible section below.

<details>
<summary>Collection Multi-Step Processing Examples</summary>

##### Example 1

Here's part of one of the solutions of ["Valid Capital Usage"](https://github.com/ncschroeder/LeetCode-Problems/blob/main/Valid_Capital_Usage.md):

```kotlin
fun detectCapitalUse(word: String): Boolean =
    when {
        ...
        else -> word.drop(2).all { it.isLowerCase() }
    }
```

`word.drop(2)` returns a string. If I decided to use sequences, I would use `word.asSequence().drop(2).all { it.isLowerCase() }`. `word.asSequence().drop(2)` returns a sequence.

##### Example 2

Here's part of one of the functions that must be implemented for ["Design Twitter"](https://github.com/ncschroeder/LeetCode-Problems/blob/main/Design_Twitter.md):

```kotlin
fun getNewsFeed(userId: Int): List<Int> {
    ...
    val tweetPredicate: (Tweet) -> Boolean = ...
    
    return tweets
        .asReversed()
        .filter(tweetPredicate)
        .take(10)
        .map { it.tweetId }
}
```

In the code above; `tweets` is an `ArrayList<Tweet>`, `asReversed` returns a `MutableList`, and `filter`, `take`, and `map` each return a `List`. If I decided to use sequences, I would use:

```kotlin
tweets
.asReversed()
.asSequence()
.filter(filterPredicate)
.take(10)
.map { it.tweetId }
.toList()
```

In the code above; `filter`, `take`, and `map` each return a `Sequence`.

</details>

</details>

## The Value of These Problems

Of course, there's some value in being able to solve these problems but I, and possibly lots of other programmers, also think that there's often some value in the *way* that problems are solved. For example, for ["Valid Capital Usage"](https://github.com/ncschroeder/LeetCode-Problems/blob/main/Valid_Capital_Usage.md), I have regex and non-regex solutions that look like:

```kotlin
fun detectCapitalUse(word: String): Boolean =
    Regex("([A-Z]?[a-z]*)|[A-Z]+") matches word
```

```kotlin
fun detectCapitalUse(word: String): Boolean =
    when {
        word.length == 1 -> true

        word.first().isLowerCase() -> word.all { it.isLowerCase() }

        word[1].isUpperCase() -> word.all { it.isUpperCase() }

        else -> word.drop(2).all { it.isLowerCase() }
    }
```

These solutions are pretty concise. I could've solved the problem with the code below, which I submitted to LeetCode and it got accepted.

```kotlin
fun detectCapitalUse(word: String): Boolean {
    var isValid = true
    if ((word.length > 1) == true) {
        if (word[0].isLowerCase() == true) {
            // I was able to write the next 4 lines of code without a single curly brace.
            for (c in word)
                if (c.isLowerCase() == false)
                    if (isValid == true) // We could just return false here but let's not.
                        isValid = false
        } else if (word[1].isUpperCase() == true) {
            for (c in word)
                if (c.isUpperCase() == false)
                    if (isValid == true)
                        isValid = false
        } else {
            /*
            If you have little or no familiarity with Kotlin, better ways to create the
            IntRange below are `2 until word.length` or `2..word.lastIndex`.
            */
            for (i in 2..word.length - 1)
                if (word[i].isLowerCase() == false)
                    if (isValid == true)
                        isValid = false
        }
    }
    if (isValid == true)
        return true
    else if (isValid == false)
        return false
    else
        return true
}
```

:rofl:


