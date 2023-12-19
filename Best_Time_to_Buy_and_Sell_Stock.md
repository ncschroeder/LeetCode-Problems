## Best Time to Buy and Sell Stock :heavy_dollar_sign: :chart_with_upwards_trend:
### Difficulty: Easy
### [Link](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)

### Description
You are given an array `prices` where `prices[i]` is the price of a given stock on the `i`<sup>th</sup> day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return `0`.

### Example 1

#### Input
`prices = [7,1,5,3,6,4]`

#### Output
`5`

#### Explanation
Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6 - 1 = 5. :money_mouth_face:

Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

### Example 2

#### Input
`prices = [7,6,4,3,1]`

#### Output
`0`

#### Explanation
In this case, no transactions are done and the max profit = 0. :frowning_face:

### Constraints
- <code>1 <= prices.length <= 10<sup>5</sup></code>
- <code>0 <= prices[i] <= 10<sup>4</sup></code>

### Original Solution
This has a $O(n^2)$ time complexity and exceeded the time limit. Some, or possibly all, LeetCode problems have a time limit and time complexity requirements are sometimes mentioned in the description.

```kotlin
fun maxProfit(prices: IntArray): Int {
    var maxProfit = 0

    for (i in 0 until prices.lastIndex) {
        for (j in i + 1..prices.lastIndex) {
            val diff: Int = prices[j] - prices[i]
            if (diff > maxProfit) {
                maxProfit = diff
            }
        }
    }

    return maxProfit
}
```

### $O(n log(n))$ Solution
Sorting is a $O(n log(n))$ operation and a binary search, a $O(log(n))$ operation, is done $n - 1$ times.

```kotlin
fun maxProfit(prices: IntArray): Int {
    val sortedFuturePrices: MutableList<Int> =
        prices
        .toMutableList()
        .apply { sort() }

    var maxProfit = 0

    for (i: Int in 0 until prices.lastIndex) {
        val curPrice: Int = prices[i]
        sortedFuturePrices.run { removeAt(index = binarySearch(curPrice)) }
        val curPriceMaxProfit: Int = sortedFuturePrices.last() - curPrice
        maxProfit = Math.max(maxProfit, curPriceMaxProfit)
    }

    return maxProfit
}
```