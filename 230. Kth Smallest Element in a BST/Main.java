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
    private int dfs(TreeNode root, int k, int[] res) {
        if (root == null) {
            return 0;
        }
        int lcount = dfs(root.left, k, res);
        if (res[0] == -1 && lcount == k - 1) {
            res[0] = root.val;
        }
        int rcount = dfs(root.right, k - lcount - 1, res);
        return lcount + rcount + 1;
    }
    public int kthSmallest(TreeNode root, int k) {
        int[] res = new int[1];
        res[0] = -1;
        dfs(root, k, res);
        return res[0];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        int res = s.kthSmallest(root, 6);
        System.out.println(res);
    }
}
