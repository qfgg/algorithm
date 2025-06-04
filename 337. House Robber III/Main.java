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
    HashMap<TreeNode, Integer> memo0 = new HashMap<>();
    HashMap<TreeNode, Integer> memo1 = new HashMap<>();
    int dfs(TreeNode root, boolean robRoot) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return robRoot ? root.val : 0;
        }
        int ret;
        if (robRoot) {
            if (memo1.containsKey(root)) {
                return memo1.get(root);
            }
            ret = root.val + dfs(root.left, false) + dfs(root.right, false);
            memo1.put(root, ret);
            return ret;
        }
        if (memo0.containsKey(root)) {
            return memo0.get(root);
        }
        ret = Math.max(dfs(root.left, true), dfs(root.left, false)) +
                Math.max(dfs(root.right, true), dfs(root.right, false));
        memo0.put(root, ret);
        return ret;
    }
    public int rob(TreeNode root) {
        return Math.max(dfs(root, true), dfs(root, false));
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(4);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        root.right.right.left = new TreeNode(2);
        int res = s.rob(root);
        System.out.println(res);
    }
}
