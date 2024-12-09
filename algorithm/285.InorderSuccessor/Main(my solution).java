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

public class Main {
    public static TreeNode[] dfs(TreeNode root, TreeNode p, TreeNode[] ans) {
        if (ans[0] != null) {
            return null;
        }
        TreeNode[] lMinMax = null;
        TreeNode[] rMinMax = null;
        if (root.left != null) {
            lMinMax = dfs(root.left, p, ans);
        }
        if (lMinMax != null && lMinMax[1] == p) {
            ans[0] = root;
        }
        if (root.right != null) {
            rMinMax = dfs(root.right, p, ans);
        }
        if (rMinMax != null && root == p) {
            ans[0] = rMinMax[0];
        }
        TreeNode lMin = lMinMax == null ? root : lMinMax[0];
        TreeNode rMax = rMinMax == null ? root : rMinMax[1];
        return new TreeNode[]{lMin, rMax};
    }
    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode[] ans = new TreeNode[1];
        dfs(root, p, ans);
        return ans[0];
    }
    public static void main(String args[]) {
        TreeNode r = new TreeNode(6);
        TreeNode p = new TreeNode(2);
        r.left = p;
        r.right = new TreeNode(10);
        r.left.left = new TreeNode(1);
        r.left.right = new TreeNode(4);
        r.left.right.left = new TreeNode(3);
        r.left.right.right = new TreeNode(5);
        r.right.left = new TreeNode(8);
        r.right.left.left = new TreeNode(7);
        r.right.left.right = new TreeNode(9);
        TreeNode ans = inorderSuccessor(r, p);
        System.out.println(ans == null ? ans : ans.val);
    }
}

//     ____6_____
//    /          \
//    2          10
//   / \        /
//  1   4      8
//     / \    / \
//    3   5  7   9
