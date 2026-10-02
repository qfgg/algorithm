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
    private boolean dfs(TreeNode root) {
        boolean needRemove;
        if (root.left != null) {
            needRemove = dfs(root.left);
            if (needRemove) {
                root.left = null;
            }
        }
        if (root.right != null) {
            needRemove = dfs(root.right);
            if (needRemove) {
                root.right = null;
            }
        }
        return root.left == null && root.right == null && root.val == 0;
    }
    public TreeNode pruneTree(TreeNode root) {
        boolean needRemove = dfs(root);
        return needRemove ? null : root;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(0);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);
        TreeNode res = s.pruneTree(root);
        System.out.println(res);
    }
}
