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
    public TreeNode dfs(TreeNode root) {
        TreeNode lroot = root.left, rroot = root.right, last = root;
        if (lroot == null && rroot == null) {
            return root;
        }
        root.left = null;
        if (lroot != null) {
            last.right = lroot;
            last = dfs(lroot);
        }
        if (rroot != null) {
            last.right = rroot;
            last = dfs(rroot);
        }
        return last;
    }
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        s.flatten(root);
        TreeNode cur = root;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.right;
        }
    }
}
