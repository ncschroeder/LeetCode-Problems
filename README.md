# Coding Challenges

Most of these are from [LeetCode.com](https://leetcode.com/) but a few were asked in interviews. At the time of this writing, I completed 95 LeetCode challenges, 4 of which are SQL ones. For the LeetCode challenges that I decided to include in this repo, I included the URL and challenge description at the beginning of each file. I decided to include the constraints if there were any important ones. For example, in one of my solutions for the Longest Common Prefix challenge, I iterate through a range of 0 to 200, exclusive, and the reason for doing that is because of a constraint. It's a constraint that explains my code so I considered it important. I decided to include examples of input and output if I thought they were helpful and/or the description isn't very clear.

The challenges that I got in interviews but are also on LeetCode are Fizz Buzz and Two Sum. The challenges that I got exclusively in interviews are Count of 2s and Reverse String. For all these challenges, I give some info in the files for those challenges about what I did in the interviews.




## Favorite Challenges

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

I used my favorite language, Kotlin, to solve the rest of the problems and implement a solution for "Linked List Cycle" that doesn't use pointers. Kotlin is statically typed and is similar to C# and Java in terms of features, though much of the syntax is different. I think Kotlin is more similar to C#; however, a notable similarity Kotlin has with Java is that Kotlin can be compiled to Java bytecode! Kotlin can also be compiled to native binaries or transpiled to front-end JavaScript. I first used Kotlin for an Android development course in college and then used it for my Singletonopoly project and then used it for LeetCode problems.

Here's a [Kotlin Docs article that gives a brief overview of syntax and some features of the language and has links to more detailed articles](https://kotlinlang.org/docs/basic-syntax.html). Here's the [Kotlin Standard Library documentation](https://kotlinlang.org/api/latest/jvm/stdlib/). You can use the menu on the left edge of either of those pages to access other documentation articles. If you go to the page for a class or function in the standard library, not only does that page describe the class or function but it also provides a link to the source code.

Some of my favorite features include [null safety](https://kotlinlang.org/docs/null-safety.html), [lambda features](https://kotlinlang.org/docs/lambdas.html#lambda-expressions-and-anonymous-functions) such as `it` being the implicit param and the last expression being the return value, [extension functions](https://kotlinlang.org/docs/extensions.html#extension-functions), destructuring, and the ability to name args when calling functions. More info about destructuring can be found at the start of the "More Kotlin Info" section below. I also like the abundance of functions that the Standard Library provides. For example, if you go to [the `Iterable` page](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/), your scrollbar will get really short because of the amount of functions there are.

<details>
<summary>More Kotlin Info</summary>

#### Destructuring

This is often done for these problems. Any object can be configured to be able to be destructured and several built-in objects are configured this way. Some of those objects that are destructured in these problems include [`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/)s, [`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/)s, [`Map.Entry`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/-entry/)s, [`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/)s, and [`IndexedValue`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-indexed-value/)s. More info about destructuring can be found in [this Kotlin Docs article](https://kotlinlang.org/docs/destructuring-declarations.html).

#### Infix Functions

These are functions that can be called by placing the function name between 2 valid expressions. These are occasionally used in these problems. An example is [`until`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/until.html) and an example usage is `0 until 9`. More info about infix functions can be found in the Kotlin Docs in the ["Infix notation" section of the "Functions" article](https://kotlinlang.org/docs/functions.html#infix-notation).

#### Lambdas

These are often used in these problems. Info about lambda syntax and features can be found in the Kotlin Docs in the ["Lambda expressions and anonymous functions" section of the "Higher-order functions and lambdas" article](https://kotlinlang.org/docs/lambdas.html#lambda-expressions-and-anonymous-functions).

#### Scope Functions

The functions [`let`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/let.html), [`run`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/run.html), [`also`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/also.html), [`apply`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/apply.html), and [`with`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/with.html) are known as *scope functions* and these are a special type of higher-order function. `let`, `also`, and `apply` are occasionally used in these problems. I used `let` to reduce variable usage and `also` and `apply` to easily create an object, immediately modify it, and store it to a variable. Examples are shown in the collapsible section below. More info about scope functions can be found in [this Kotlin Docs article](https://kotlinlang.org/docs/scope-functions.html).

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

##### `also`

In ["House Robber"](https://github.com/ncschroeder/LeetCode-Problems/blob/main/House_Robber.md), this code snippet is used:

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

In ["Get Watched Videos by Your Friends"](https://github.com/ncschroeder/LeetCode-Problems/blob/main/Get_Watched_Videos_by_Your_Friends.md), this code snippet is used:

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

The `run`, `apply`, and `with` scope functions have a param named `block` and the type of it is `T.() -> Unit`, where `T` is a type param. The [`buildString`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/build-string.html) and [`buildList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/build-list.html) functions have a param named `builderAction` and the type of the `buildString` one is `StringBuilder.() -> Unit` and the type of the `buildList` one is `MutableList<E>.() -> Unit`, where `E` is a type param. These are functions with a receiver and the receiver type is the type before the `.()`. This type of function can be created with a lambda and inside that lambda, you can access members of the receiver type without needing an instance of that type. For example, here's a usage of `buildList` in ["Valid Sudoku"](https://github.com/ncschroeder/LeetCode-Problems/blob/main/Valid_Sudoku.md):

```kotlin
fun getBoxContents(startRow: Int, startCol: Int): List<Int> =
    buildList(capacity = 9) {
        for (row: Int in startRow until startRow + 3) {
            for (col: Int in startCol until startCol + 3) {
                add(board[row][col])
            }
        }
    }
```

[`add`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/add.html) is a `MutableList` function that would otherwise be called like `someMutableList.add(...)`.

`buildList` is also used in ["Find Tic-Tac-Toe Winner"](https://github.com/ncschroeder/LeetCode-Problems/blob/main/Find_Tic-Tac-Toe_Winner.md) and `buildString` is used in ["Merge Strings Alternately"](https://github.com/ncschroeder/LeetCode-Problems/blob/main/Merge_Strings_Alternately.md).

More info about functions with receivers can be found in the Kotlin Docs in the 2<sup>nd</sup> bullet point of the ["Function types" section](https://kotlinlang.org/docs/lambdas.html#function-types) and the ["Function literals with receiver" section](https://kotlinlang.org/docs/lambdas.html#function-literals-with-receiver) of the "Higher-order functions and lambdas" article.

#### Eager and Lazy Collection Operations

Some collection operations, such as mapping and filtering, can be either eagerly or lazily evaluated. If one of those operations is performed on an iterable, array, string, or map; it'll be eagerly evaluated and if performed on a sequence, it'll be lazily evaluated. [`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/) and [`Sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/) are interfaces that have a type param `T` and require an implementation of `operator fun iterator(): Iterator<T>`. `Iterable` is the interface that types such as `List`, `Set`, and `IntRange` implement. As implied a few sentences ago; arrays, strings, and maps aren't iterables. An iterable, array, string, or map can be converted to a sequence by using the [`asSequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/as-sequence.html) function, though there are a few other ways to create a sequence. More info can be found in the Kotlin Docs in [the "Sequences" article](https://kotlinlang.org/docs/sequences.html), which mostly goes over sequences and lazy evaluation but also goes over iterables and eager evaluation.

The 4<sup>th</sup> paragraph of that article is important and here it is:

> So, the sequences let you avoid building results of intermediate steps, therefore improving the performance of the whole collection processing chain. However, the lazy nature of sequences adds some overhead which may be significant when processing smaller collections or doing simpler computations. Hence, you should consider both `Sequence` and `Iterable` and decide which one is better for your case.

When a solution is submitted to LeetCode, lots of test cases with various input collection sizes will be ran with that solution. If we do operations with an input collection that have intermediate steps, whether it's better to use iterables or sequences depends on the collection size and complexity of the operations, as implied in the article. Because of this, there doesn't seem to be a "right" choice when it comes to choosing between iterables or sequences for LeetCode problems. Besides, these problems are for practice and/or entertainment anyway and not for software.

It's often more convenient to use iterables since input collections are usually given to us in the form of arrays and strings. Because of that, we would have to use the `asSequence` function if we wanted to use sequences.

There are solutions to several problems in this repo where I do collection operations that have intermediate steps. For the above reasons, I decided to use iterables, arrays, and strings to do those operations, which results in eager evaluation and intermediate collections. Shown in the collapsible section below are 3 examples of those operations and how sequences could be used instead.

<details>
<summary>Examples</summary>

I'm going to use the terms *intermediate* and *terminal* to describe operations. An excerpt from the ["Sequence operations" section in the aforementioned "Sequences" article](https://kotlinlang.org/docs/sequences.html#sequence-operations) is:

> If a sequence operation returns another sequence, which is produced lazily, it's called **intermediate**. Otherwise, the operation is **terminal**. Examples of terminal operations are `toList()` or `sum()`. Sequence elements can be retrieved only with terminal operations.

##### [Valid Capital Usage](https://github.com/ncschroeder/LeetCode-Problems/blob/main/Valid_Capital_Usage.md)

Here's part of the solution:

```kotlin
fun detectCapitalUse(word: String): Boolean =
    when {
        ...
        else -> word.drop(2).all { ... }
    }
```

`word.drop(2)` returns a new string. `all` is a terminal operation so that last expression could use sequences with `word.asSequence().drop(2).all { ... }`.

##### [Design Twitter](https://github.com/ncschroeder/LeetCode-Problems/blob/main/Design_Twitter.md)

Here's one of the functions that must be implemented to solve this problem:

```kotlin
fun getNewsFeed(userId: Int): List<Int> {
    ...
    
    return tweets // This is an ArrayList.
        .asReversed()
        .filter { ... }
        .take(10)
        .map { ... }
}
```

The `filter`, `take`, and `map` functions each return a list. `map` is an intermediate operation so the return expression could use sequences with:

```kotlin
tweets
.asReversed()
.asSequence()
.filter { ... }
.take(10)
.map { ... }
.toList()
```

##### [Find Tic-Tac-Toe Winner](https://github.com/ncschroeder/LeetCode-Problems/blob/main/Find_Tic-Tac-Toe_Winner.md)

Below is part of the ending of the solution. The [`buildList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/build-list.html) function is mentioned in the "Functions with Receivers" section above.

```kotlin
val groups: List<List<String?>> =
    buildList(capacity = 8) {
        addAll(...)

        for (...) {
            add(...)
        }

        add(...)
        add(...)
    }

return groups
    .firstOrNull { ... }
    ?.first()
    ?: ...
```

`groups` is a starting collection and not an intermediate collection but a sequence that yields the same elements as `groups` could be made using the [`sequence`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/sequence.html) function mentioned in ["Construct" section in the aforementioned "Sequences" article](https://kotlinlang.org/docs/sequences.html#from-chunks). This function has a param named `block` and the type of it is `suspend SequenceScope<T>.() -> Unit`, where `T` is a type param. This is a function with a receiver and like with the `buildList` function, more info about functions with receivers can be found in the "Functions with Receivers" section above.

Here's how the `sequence` function could be used:

```kotlin
val groups: Sequence<List<String?>> =
    sequence {
        yieldAll(...)

        for (...) {
            yield(...)
        }

        yield(...)
        yield(...)
    }

return groups
    .firstOrNull { ... }
    ?.first()
    ?: ...
```

</details>

</details>

## Refactoring

There was some refactoring done on the code used for these challenges. Most of it was what I considered to be minor so for many challenges, there's 1 solution that includes the refactored code. There are a few challenges where I had an original solution and a refactored solution and I thought of them as different enough that I decided to include both.

Also, LeetCode uses an undisclosed old version of Kotlin that doesn't have all the functions that Kotlin currently has. Some of these include the [`Char` extension function `digitToInt`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/digit-to-int.html) and the extension functions [`max`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/max.html) and [`sumOf`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sum-of.html), which can be used on multiple classes and interfaces. Obviously, I couldn't use these in my LeetCode submissions but I did use them in the code in this repo. So if you try to cheat by copying and pasting my code into your own LeetCode submissions, or if you just try to use my code for a submission to see if it works without having any intention of cheating, you might get a compilation error for that reason. :smiley: