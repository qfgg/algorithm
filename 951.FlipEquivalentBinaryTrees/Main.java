import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class Solution {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if ((root1 == null && root2 != null) ||
                (root1 != null && root2 == null) ||
                root1.val != root2.val) {
            return false;
        }
        return (flipEquiv(root1.left, root2.left) &&
                flipEquiv(root1.right, root2.right)) ||
                (flipEquiv(root1.left, root2.right) &&
                        flipEquiv(root1.right, root2.left));
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.left.right.left = new TreeNode(7);
        root1.left.right.right = new TreeNode(8);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(6);
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(3);
        root2.left.right = new TreeNode(6);
        root2.right = new TreeNode(2);
        root2.right.left = new TreeNode(4);
        root2.right.right = new TreeNode(5);
        root2.right.right.left = new TreeNode(8);
        root2.right.right.right = new TreeNode(7);
        boolean res = s.flipEquiv(root1, root1);
        System.out.println(res);
    }
}
//     ____1_____
//    /          \
//    2          3
//   / \        /
//  4   5      6
//     / \
//    7   8