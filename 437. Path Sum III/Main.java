import java.util.*;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class Solution {
    private void dfs(TreeNode root, int targetSum, long presum, Map<Long, Integer> presumCnt, int[] ret) {
        if (presumCnt.containsKey(root.val + presum - targetSum)) {
            ret[0] += presumCnt.get(root.val + presum - targetSum);
        }
        if (root.val + presum == targetSum) {
            ret[0]++;
        }
        long lastPresum;
        if (root.left != null) {
            lastPresum = presum + root.val;
            if (presumCnt.containsKey(lastPresum)) {
                presumCnt.put(lastPresum, presumCnt.get(lastPresum) + 1);
            } else {
                presumCnt.put(lastPresum, 1);
            }
            dfs(root.left, targetSum, lastPresum, presumCnt, ret);
            if (presumCnt.get(lastPresum) == 1) {
                presumCnt.remove(lastPresum);
            } else {
                presumCnt.put(lastPresum, presumCnt.get(lastPresum) - 1);
            }
        }
        if (root.right != null) {
            lastPresum = presum + root.val;
            if (presumCnt.containsKey(lastPresum)) {
                presumCnt.put(lastPresum, presumCnt.get(lastPresum) + 1);
            } else {
                presumCnt.put(lastPresum, 1);
            }
            dfs(root.right, targetSum, lastPresum, presumCnt, ret);
            if (presumCnt.get(lastPresum) == 1) {
                presumCnt.remove(lastPresum);
            } else {
                presumCnt.put(lastPresum, presumCnt.get(lastPresum) - 1);
            }
        }
    }
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        Map<Long, Integer> presumCnt = new HashMap<>();
        int[] ret = new int[1];
        dfs(root, targetSum, 0, presumCnt, ret);
        return ret[0];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);
        root.right.right = new TreeNode(11);
        int res = s.pathSum(root, 8);
        System.out.println(res);
    }
}
