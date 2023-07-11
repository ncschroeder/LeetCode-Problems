/*
https://leetcode.com/problems/two-sum/

Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

Follow-up: Can you come up with an algorithm that is less than O(n^2) time complexity? Yes
*/

/*
Simple O(n^2) solution

I actually got this same challenge in an interview, though there were a couple minor differences. I had to print the pairs instead of returning an
int array and there was a possibility of there being multiple solutions. I used the JavaScript equivalent of this solution for that.
*/
fun twoSum1(nums: IntArray, target: Int): IntArray {
    for (i in 0 until nums.lastIndex) {
        val intToFind = target - nums[i]
        for (j in i + 1..nums.lastIndex) {
            if (nums[j] == intToFind) {
                return intArrayOf(i, j)
            }
        }
    }

    throw Exception("No answer found")
}


// O(n) solution
fun twoSum2(nums: IntArray, target: Int): IntArray {
    // If the target is even, then it's possible that there are 2 elements in nums that are the same that
    // add up to the target
    if (target % 2 == 0) {
        val halfOfTarget: Int = target / 2
        val halfOfTargetIndices: List<Int> =
            nums.indices.filter { nums[it] == halfOfTarget }
    
        if (halfOfTargetIndices.size == 2) {
            return halfOfTargetIndices.toIntArray()
        }
    }
    
    /*
    Unless the elements that sum to the target are the same, there can't be duplicates of elements that are
    part of the solution because if there were, then there would be more than 1 valid solution. Make a Map
    where the keys are the elements and the values are the indices of those elements. If there are duplicates,
    I believe the associate function will map those elements to the last index they were found at, but those
    elements don't matter.
    */
    val elementsAndIndices: Map<Int, Int> =
        nums.withIndex().associate { (index, element) -> element to index }
    
    for ((index1, element) in nums.withIndex()) {
        val intToFind = target - element
        val index2: Int? = elementsAndIndices[intToFind]

        // If the target is even and target / 2 is an element, then intToFind will be the same as that element
        // so index2 will be equal to index1
        if (index2 != null && element != intToFind) {
            return intArrayOf(index1, index2)
        }
    }

    throw Exception("No answer found")
}