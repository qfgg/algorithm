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
    public static boolean dfs(TreeNode root, int curMin, int curMax, boolean isMinChanged, boolean isMaxChanged) {
        if ((isMinChanged && root.val == curMin) ||
                (isMaxChanged && root.val == curMax) ||
                root.val < curMin ||
                root.val > curMax) {
            return false;
        }
        boolean isLeftValid = true;
        boolean isRightValid = true;
        if (root.left != null) {
            isLeftValid = dfs(root.left, curMin, root.val, isMinChanged, true);
        }
        if (root.right != null) {
            isRightValid = dfs(root.right, root.val, curMax, true, isMaxChanged);
        }
        return isLeftValid && isRightValid;
    }
    public static boolean isValidBST(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE, false, false);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(6);
        root.left.right.right = new TreeNode(3);
        boolean ret = isValidBST(root);
        System.out.println(ret);
    }
}
