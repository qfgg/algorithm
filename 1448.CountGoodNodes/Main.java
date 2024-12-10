class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
 }

public class Main {
    public static int dfs(TreeNode root, int curMax) {
        if (root == null) {
            return 0;
        }
        if (root.val >= curMax) {
            curMax = root.val;
            return 1 + dfs(root.left, curMax) + dfs(root.right, curMax);
        }
        return dfs(root.left, curMax) + dfs(root.right, curMax);
    }
    public static int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.left.right = null;
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(5);
        int ret = goodNodes(root);
        System.out.println(ret);
    }
}
