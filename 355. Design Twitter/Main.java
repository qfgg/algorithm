import java.util.*;


class Twitter {
    List<int[]> posts;
    boolean[][] followMap;
    public Twitter() {
        posts = new ArrayList<>();
        followMap = new boolean[501][501];
    }

    public void postTweet(int userId, int tweetId) {
        posts.add(new int[]{tweetId, userId});
    }

    public List<Integer> getNewsFeed(int userId) {
        int i = posts.size() - 1;
        List<Integer> res = new ArrayList<>();
        int[] curPost;
        while (i >= 0 && res.size() < 10) {
            curPost = posts.get(i);
            if (userId == curPost[1] || followMap[userId][curPost[1]]) {
                res.add(curPost[0]);
            }
            i--;
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        followMap[followerId][followeeId] = true;
    }

    public void unfollow(int followerId, int followeeId) {
        followMap[followerId][followeeId] = false;
    }
}
public class Main {
    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
        System.out.println(twitter.getNewsFeed(1));  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
        twitter.follow(1, 2);    // User 1 follows user 2.
        twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
        System.out.println(twitter.getNewsFeed(1));  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
        twitter.unfollow(1, 2);  // User 1 unfollows user 2.
        System.out.println(twitter.getNewsFeed(1));  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
    }
}
