## Assign Cookies to Kids :cookie: :family_man_woman_girl_boy:
### Difficulty: Easy
### [Link](https://leetcode.com/problems/assign-cookies/)

### Description

Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.

Each child `i` has a greed factor `g[i]`, which is the minimum size of a cookie that the child will be content with; and each cookie `j` has a size `s[j]`. If `s[j] >= g[i]`, we can assign the cookie `j` to the child `i`, and the child `i` will be content. Your goal is to maximize the number of your content children and output the maximum number.

### Example 1

#### Input
`g = [1,2,3], s = [1,1]`

#### Output
`1`

#### Explanation

You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3. And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content. You need to output 1.

### Example 2

#### Input
`g = [1,2], s = [1,2,3]`

#### Output
`2`

#### Explanation

You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2. You have 3 cookies and their sizes are big enough to gratify all of the children, You need to output 2.

### Constraints
- <code>1 <= g.length <= 3 * 10<sup>4</sup></code>
- <code>0 <= s.length <= 3 * 10<sup>4</sup></code>
- <code>1 <= g[i], s[j] <= 2<sup>31</sup> - 1</code>

### Refactored Solutions

I first came up with the descending iteration solution and eventually realized that there's an ascending iteration solution as well. I decided to include both since I think it's interesting that there's 1 solution where you can have a greed factor iterator and iterate through cookie sizes in a for loop and a different solution where you can have a cookie size iterator and iterate through greed factors in a for loop.

The ascending iteration solution has better performance since the [`sortedArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sorted-array.html) function creates a new array and sorts it while [`sortedArrayDescending`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/sorted-array-descending.html) creates a new array, sorts it, and reverses it.

#### Ascending Iteration Solution

```kotlin
fun findContentChildren(g: IntArray, s: IntArray): Int {        
    val greedFactorIterator: Iterator<Int> = g.sortedArray().iterator()
    var greedFactor: Int = greedFactorIterator.next()
    var numKidsContent = 0
    
    for (cookieSize: Int in s.sortedArray()) {
        if (cookieSize >= greedFactor) {
            numKidsContent++
            if (!greedFactorIterator.hasNext()) break
            greedFactor = greedFactorIterator.next()
        }
    }
    
    return numKidsContent
}
```

#### Descending Iteration Solution


```kotlin
fun findContentChildren(g: IntArray, s: IntArray): Int {
    if (s.isEmpty()) return 0

    val cookieSizeIterator: Iterator<Int> = s.sortedArrayDescending().iterator()
    var cookieSize: Int = cookieSizeIterator.next()
    var numKidsContent = 0
    
    for (greedFactor: Int in g.sortedArrayDescending()) {
        if (greedFactor <= cookieSize) {
            numKidsContent++
            if (!cookieSizeIterator.hasNext()) break
            cookieSize = cookieSizeIterator.next()
        }
    }
    
    return numKidsContent
}
```

### Original Solution

In this solution, I used deques and realized that using deques in this situation is unnecessary since I'm just using and removing the last elements of them without adding any elements to them. An iterator and a for loop can be used instead so that's why those are being used in the solutions above.

```kotlin
fun findContentChildren(g: IntArray, s: IntArray): Int {
    val gStack = g.toSortedDeque()
    val sStack = s.toSortedDeque()
    var count = 0
    
    while (gStack.isNotEmpty() && sStack.isNotEmpty()) {
        val topSize = sStack.removeLast()
        while (gStack.isNotEmpty()) {
            val topG = gStack.removeLast()
            if (topG <= topSize) {
                count++
                break
            }
        }
    }
    
    return count
}

fun IntArray.toSortedDeque(): ArrayDeque<Int> =
    apply { sort() }
    .asList()
    .let { ArrayDeque(elements = it) }
```
