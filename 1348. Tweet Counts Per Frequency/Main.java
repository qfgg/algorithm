import java.util.*;


class Node {
    Node left;
    Node right;
    int count;
    Node() {
        count = 0;
    }
}
class Tree {
    private Node root;
    private int l, u;
    Tree(int lower, int upper) {
        root = new Node();
        l = lower;
        u = upper;
    }
    int getCount(Node node) {
        return node == null ? 0 : node.count;
    }
    void addRange(Node node, int lower, int upper, int index) {
        if (lower == upper) {
            node.count++;
            return;
        }
        int mid = (lower + upper) / 2;
        if (index <= mid) {
            if (node.left == null) {
                node.left = new Node();
            }
            addRange(node.left, lower, mid, index);
        } else {
            if (node.right == null) {
                node.right = new Node();
            }
            addRange(node.right, mid + 1, upper, index);
        }
        node.count = getCount(node.left) + getCount(node.right);
    }
    void add(int index) {
        addRange(root, l, u, index);
    }
    int queryRange(Node node, int lower, int upper, int ql, int qr) {
        if (node == null || upper < ql || lower > qr) {
            return 0;
        }
        if (ql <= lower && qr >= upper) {
            return node.count;
        }
        int mid = (lower + upper) / 2;
        return queryRange(node.left, lower, mid, ql, qr) + queryRange(node.right, mid + 1, upper, ql, qr);
    }
    int query(int ql, int qr) {
        return queryRange(root, l, u, ql, qr);
    }
}
class TweetCounts {
    HashMap<String, Tree> map;

    public TweetCounts() {
        map = new HashMap<>();
    }

    public void recordTweet(String tweetName, int time) {
        Tree tr;
        if (map.containsKey(tweetName)) {
            tr = map.get(tweetName);
        } else {
            tr = new Tree(0, (int)1e9);
            map.put(tweetName, tr);
        }
        tr.add(time);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> cnt = new ArrayList<>();
        int span = freq.equals("minute") ? 60 : freq.equals("hour") ? 3600 : 86400;
        int curTime, curCount;
        Tree tr = map.getOrDefault(tweetName, null);
        for (curTime = startTime; curTime <= endTime; curTime = curTime + span) {
            curCount = tr == null ? 0 : tr.query(curTime, Math.min(endTime, curTime + span - 1));
            cnt.add(curCount);
        }
        return cnt;
    }
}
public class Main {
    public static void main(String[] args) {
        TweetCounts tweetCounts = new TweetCounts();
        tweetCounts.recordTweet("tweet3", 0);
        tweetCounts.recordTweet("tweet3", 60);
        tweetCounts.recordTweet("tweet3", 10);
        System.out.println(tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59));
        System.out.println(tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60));
        tweetCounts.recordTweet("tweet3", 120);
        System.out.println(tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210));
    }
}
