/*
https://leetcode.com/problems/design-twitter/

Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.

Implement the Twitter class:
- Twitter() - Initializes your twitter object.
- void postTweet(int userId, int tweetId) - Composes a new tweet with ID tweetId by the user userId. Each call to this function will be made with a unique tweetId.
- List<Int> getNewsFeed(int userId) - Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
- void follow(int followerId, int followeeId) - The user with ID followerId started following the user with ID followeeId.
- void unfollow(int followerId, int followeeId) - The user with ID followerId started unfollowing the user with ID followeeId.

Constraints:
- 1 <= userId, followerId, followeeId <= 500
- 0 <= tweetId <= 10^4
- All the tweets have unique IDs
- At most 3 * 10^4 calls will be made to postTweet, getNewsFeed, follow, and unfollow
*/

class Twitter {
    data class Tweet(val userId: Int, val tweetId: Int)

    val tweets = mutableListOf<Tweet>()

    /*
    Let usersAndFollowing be a map where the keys are all ints in the range of 1 to 500, inclusive.
    These are for user IDs. The values are sets of the IDs of the users that are followed by the user
    whose ID is the key.
    */
    val usersAndFollowing: Map<Int, MutableSet<Int>> =
        (1..500).associateWith { mutableSetOf<Int>() }

    fun postTweet(userId: Int, tweetId: Int) {
        tweets.add(Tweet(userId, tweetId))
    }

    fun getNewsFeed(userId: Int): List<Int> {
        val followingIds: Set<Int> = usersAndFollowing.getValue(userId)
        return tweets.asReversed().asSequence()
            .filter { it.userId == userId || it.userId in followingIds }
            .take(10)
            .map { it.tweetId }
            .toList()
    }

    fun follow(followerId: Int, followeeId: Int) {
        usersAndFollowing.getValue(followerId).add(followeeId)
    }

    fun unfollow(followerId: Int, followeeId: Int) {
        usersAndFollowing.getValue(followerId).remove(followeeId)
    }
}