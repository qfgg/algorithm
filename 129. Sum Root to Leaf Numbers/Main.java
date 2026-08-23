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
    private int dfs(TreeNode root, int[] res) {
        int ret = 0;
        if (root.left != null) {
            ret += dfs(root.left, res) * 10;
        }
        if (root.right != null) {
            ret += dfs(root.right, res) * 10;
        }
        ret = ret == 0 ? 1 : ret;
        res[0] += ret * root.val;
        return ret;
    }
    public int sumNumbers(TreeNode root) {
        int[] res = new int[1];
        dfs(root, res);
        return res[0];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(9);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);
        int res = s.sumNumbers(root);
        System.out.println(res);
    }
}
