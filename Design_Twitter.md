## Design Twitter :baby_chick:
### Difficulty: Medium
### [Link](https://leetcode.com/problems/design-twitter/)

As mentioned in the README, this is a unique problem since it's 1 of 3 LeetCode problems I've done, and the only problem in this repo, that requires you to implement a class.

### Description
Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.

Implement the `Twitter` class:
- `Twitter()` - Initializes your twitter object.
- `void postTweet(int userId, int tweetId)` - Composes a new tweet with ID `tweetId` by the user userId. Each call to this function will be made with a unique `tweetId`.
- `List<Int> getNewsFeed(int userId)` - Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
- `void follow(int followerId, int followeeId)` - The user with ID `followerId` started following the user with ID `followeeId`.
- `void unfollow(int followerId, int followeeId)` - The user with ID `followerId` started unfollowing the user with ID `followeeId`.

### Example

#### Input
```
["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
[[],        [1, 5],      [1],           [1, 2],   [2, 6],      [1],           [1, 2],     [1]]
```

#### Output
`[null,     null,        [5],           null,     null,        [6, 5],        null,       [5]]`

#### Explanation

```kotlin
val twitter = Twitter()
twitter.postTweet(1, 5) // User 1 posts a new tweet (id = 5).
twitter.getNewsFeed(1)  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
twitter.follow(1, 2)    // User 1 follows user 2.
twitter.postTweet(2, 6) // User 2 posts a new tweet (id = 6).
twitter.getNewsFeed(1)  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.unfollow(1, 2)  // User 1 unfollows user 2.
twitter.getNewsFeed(1)  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
```

### Constraints
- `1 <= userId, followerId, followeeId <= 500`
- <code>0 <= tweetId <= 10<sup>4</sup></code>
- All the tweets have unique IDs.
- At most 3 * 10<sup>4</sup> calls will be made to `postTweet`, `getNewsFeed`, `follow`, and `unfollow`.

### Solution

```kotlin
class Twitter {
    data class Tweet(val userId: Int, val tweetId: Int)

    val tweets = ArrayList<Tweet>()

    /*
    Let usersAndFollowing be a map where the keys are all ints in the range of 1 to 500, inclusive. These are for user IDs.
    The 1st constraint says that user IDs are in that range. The values are sets of the IDs of the users that are followed
    by the user whose ID is the key.
    */
    val usersAndFollowing: Map<Int, MutableSet<Int>> =
        (1..500).associateWith { HashSet() }

    fun postTweet(userId: Int, tweetId: Int) {
        tweets.add(Tweet(userId, tweetId))
    }

    fun getNewsFeed(userId: Int): List<Int> {
        val followingIds: Set<Int> = usersAndFollowing.getValue(userId)
        
        return tweets
            .asReversed()
            .filter { it.userId == userId || it.userId in followingIds }
            .take(10)
            .map { it.tweetId }
    }

    fun follow(followerId: Int, followeeId: Int) {
        usersAndFollowing
        .getValue(followerId)
        .add(followeeId)
    }

    fun unfollow(followerId: Int, followeeId: Int) {
        usersAndFollowing
        .getValue(followerId)
        .remove(followeeId)
    }
}
```