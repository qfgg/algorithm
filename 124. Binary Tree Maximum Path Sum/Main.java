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
    private int maxPathFromRoot(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        }
        if (root.left == null) {
            return root.val + Math.max(maxPathFromRoot(root.right), 0);
        }
        if (root.right == null) {
            return root.val + Math.max(maxPathFromRoot(root.left), 0);
        }
        return root.val + Math.max(Math.max(maxPathFromRoot(root.left), maxPathFromRoot(root.right)), 0);
    }
    public int maxPathSum(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        }
        if (root.left == null || root.right == null) {
            return Math.max(maxPathFromRoot(root), maxPathSum(root.left == null ? root.right : root.left));
        }
        int throughRoot = root.val + Math.max(maxPathFromRoot(root.left), 0) + Math.max(maxPathFromRoot(root.right), 0);
        int onlyLeft = maxPathSum(root.left);
        int onlyRight = maxPathSum(root.right);
        return Math.max(throughRoot, Math.max(onlyLeft, onlyRight));
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(-30);
        root.left = new TreeNode(-10);
        root.left.left = new TreeNode(40);
        root.right = new TreeNode(-20);
        root.right.left = new TreeNode(60);
        root.right.left.left = new TreeNode(20);
        root.right.left.right = new TreeNode(-10);
        root.right.left.left.left = new TreeNode(-100);
        int res = s.maxPathSum(root);
        System.out.println(res);
    }
}
