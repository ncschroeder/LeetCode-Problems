# Coding Challenges

Most of these are from [LeetCode.com](https://leetcode.com/) but a few were asked in interviews. At the time of this writing, I completed 95 LeetCode challenges, 4 of which are SQL ones. For the LeetCode challenges that I decided to include in this repo, I included the URL and challenge description at the beginning of each file. I decided to include the constraints if there were any important ones. For example, in one of my solutions for the Longest Common Prefix challenge, I iterate through a range of 0 to 200, exclusive, and the reason for doing that is because of a constraint. It's a constraint that explains my code so I considered it important. I decided to include examples of input and output if I thought they were helpful and/or the description isn't very clear.

The challenges that I got in interviews but are also on LeetCode are Fizz Buzz and Two Sum. The challenges that I got exclusively in interviews are Count of 2s and Reverse String. For all these challenges, I give some info in the files for those challenges about what I did in the interviews.

## Language Used

I did all these challenges with my favorite language, Kotlin. It runs on the JVM and is similar to C#. It's statically typed and has some neat features, such as null safety, smart casting, destructuring for some objects, the ability to name args, and an abundance of built-in functions (many of which are higher-order) to name a few. Here's a [*Kotlin Docs* article that gives a brief overview of syntax and some features of the language and has links to more detailed articles](https://kotlinlang.org/docs/basic-syntax.html). Here's the [documentation for the Kotlin Standard Library](https://kotlinlang.org/api/latest/jvm/stdlib/). You can use the menu on the left edge of either of these pages to access other documentation articles.

Lambdas are used often in these challenges and Kotlin has a pretty unique syntax for them. There's a *Kotlin Docs* article that goes over higher-order functions and lambdas. It starts off by going over a bit of general info about higher-order functions. Info about lambda syntax and features is in this article in the "Lambda expression syntax" section and the sections below that. Here's [the link to that article](https://kotlinlang.org/docs/lambdas.html). Other notable features used that aren't easily findable in the documentation include [destructuring](https://kotlinlang.org/docs/destructuring-declarations.html) and [scope functions](https://kotlinlang.org/docs/scope-functions.html).

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

## Refactoring

There was some refactoring done on the code used for these challenges. Most of it was what I considered to be minor so for many challenges, there's 1 solution that includes the refactored code. There are a few challenges where I had an original solution and a refactored solution and I thought of them as different enough that I decided to include both.

Also, LeetCode uses an undisclosed old version of Kotlin that doesn't have all the functions that Kotlin currently has. Some of these include the [`Char` extension function `digitToInt`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/digit-to-int.html) and the extension functions [`max`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/max.html) and [`sumOf`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sum-of.html), which can be used on multiple classes and interfaces. Obviously, I couldn't use these in my LeetCode submissions but I did use them in the code in this repo. So if you try to cheat by copying and pasting my code into your own LeetCode submissions, or if you just try to use my code for a submission to see if it works without having any intention of cheating, you might get a compilation error for that reason. :smiley: