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
    private boolean[] dfs(TreeNode root, TreeNode p, TreeNode q, List<TreeNode> res) {
        boolean[] check = new boolean[2];
        if (root == p) {
            check[0] = true;
        }
        if (root == q) {
            check[1] = true;
        }
        boolean[] l = null, r = null;
        if (root.left != null) {
            l = dfs(root.left, p, q, res);
        }
        if (root.right != null) {
            r = dfs(root.right, p, q, res);
        }
        if (l != null) {
            check[0] = l[0] || check[0];
            check[1] = l[1] || check[1];
        }
        if (r != null) {
            check[0] = r[0] || check[0];
            check[1] = r[1] || check[1];
        }
        if (check[0] && check[1] && res.isEmpty()) {
            res.add(root);
        }
        return check;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> res = new ArrayList<>();
        dfs(root, p, q, res);
        return res.get(0);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        TreeNode res = s.lowestCommonAncestor(root, root.right, root.right.right);
        System.out.println(res.val);
    }
}
