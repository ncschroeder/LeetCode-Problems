/*
https://leetcode.com/problems/best-poker-hand/

You are given an integer array `ranks` and a character array `suits`. You have 5 cards where the ith card has a rank of ranks[i] and a suit of suits[i].

The following are the types of poker hands you can make from best to worst:
"Flush": Five cards of the same suit.
"Three of a Kind": Three cards of the same rank.
"Pair": Two cards of the same rank.
"High Card": Any single card.

Return a string representing the best type of poker hand you can make with the given cards.

Note that the return values are case-sensitive.
*/

fun bestHand(ranks: IntArray, suits: CharArray): String {
    // Check if all suits are the same
    if (suits.all { it == suits.first() }) {
        return "Flush"
    }
    
    // Let rankCounts be a map where the keys are the ranks and the values are the counts for how many
    // cards have that rank
    val rankCounts = mutableMapOf<Int, Int>()
    for (r in ranks) {
        rankCounts[r] = (rankCounts[r] ?: 0) + 1
    }

    return when (rankCounts.values.max()) {
        in 3..5 -> "Three of a Kind"
        2 -> "Pair"
        else -> "High Card"
    }
}