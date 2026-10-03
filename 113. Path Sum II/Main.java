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
    private void dfs(TreeNode root, int targetSum, int sum, List<Integer> path, List<List<Integer>> ret) {
        if (sum == targetSum && root.left == null && root.right == null) {
            ret.add(new ArrayList<>(path));
            return;
        }
        if (root.left != null) {
            path.add(root.left.val);
            dfs(root.left, targetSum, sum + root.left.val, path, ret);
            path.removeLast();
        }
        if (root.right != null) {
            path.add(root.right.val);
            dfs(root.right, targetSum, sum + root.right.val, path, ret);
            path.removeLast();
        }
    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        List<Integer> path = new ArrayList<>();
        path.add(root.val);
        dfs(root, targetSum, root.val, path, ret);
        return ret;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        List<List<Integer>> res = s.pathSum(root, 22);
        System.out.println(res);
    }
}
