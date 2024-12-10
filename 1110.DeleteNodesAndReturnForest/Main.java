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
    public static TreeNode delete(TreeNode root, Set<Integer> del, List<TreeNode> roots) {
        TreeNode r = root;
        if (del.contains(root.val)) {
            r = null;
        }
        root.left = root.left == null ? null : delete(root.left, del, roots);
        root.right = root.right == null ? null : delete(root.right, del, roots);
        if (r == null) {
            if (root.left != null) {
                roots.add(root.left);
            }
            if (root.right != null) {
                roots.add(root.right);
            }
        }
        return r;
    }
    public static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> del = new HashSet<>();
        for (int td : to_delete) {
            del.add(td);
        }
        List<TreeNode> roots = new ArrayList<>();
        if (!del.contains(root.val)) {
            roots.add(root);
        }
        delete(root, del, roots);
        return roots;
    }
    public static void main(String[] args) {
        TreeNode r = new TreeNode(6);
        r.left = new TreeNode(2);
        r.right = new TreeNode(10);
        r.left.left = new TreeNode(1);
        r.left.right = new TreeNode(4);
        r.left.right.left = new TreeNode(3);
        r.left.right.right = new TreeNode(5);
        r.right.left = new TreeNode(8);
        r.right.left.left = new TreeNode(7);
        r.right.left.right = new TreeNode(9);
        List<TreeNode> ans = delNodes(r, new int[]{4,7,8});
        for(TreeNode t : ans) {
            System.out.print(t.val);
            System.out.print(" ");
        }
        System.out.println();
    }
}
//     ____6_____
//    /          \
//    2          10
//   / \        /
//  1   4      8
//     / \    / \
//    3   5  7   9
