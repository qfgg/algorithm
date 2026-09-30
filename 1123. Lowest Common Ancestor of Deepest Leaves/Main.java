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
    private int dfs(TreeNode root, int depth, TreeNode[] ret) {
        if (root.left == null && root.right == null) {
            ret[0] = root;
            return depth;
        }
        int lMaxDepth = 0, rMaxDepth = 0;
        TreeNode[] lRet = new TreeNode[1], rRet = new TreeNode[1];
        if (root.left != null) {
            lMaxDepth = dfs(root.left, depth + 1, lRet);
        }
        if (root.right != null) {
            rMaxDepth = dfs(root.right, depth + 1, rRet);
        }
        if (lMaxDepth > rMaxDepth) {
            ret[0] = lRet[0];
            return lMaxDepth;
        }
        if (lMaxDepth < rMaxDepth) {
            ret[0] = rRet[0];
            return rMaxDepth;
        }
        ret[0] = root;
        return lMaxDepth;
    }
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        TreeNode[] ret = new TreeNode[1];
        dfs(root, 0, ret);
        return ret[0];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        TreeNode res = s.lcaDeepestLeaves(root);
        System.out.println(res.val);
    }
}
