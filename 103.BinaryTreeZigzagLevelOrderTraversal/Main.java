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
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        List<Integer> rowVals = new ArrayList<>();
        Stack<TreeNode> row = new Stack<>();
        Stack<TreeNode> next = new Stack<>();
        Stack<TreeNode> tmp;
        row.push(root);
        boolean l2r = true;
        TreeNode cur;
        while (!row.isEmpty()) {
            cur = row.pop();
            rowVals.add(cur.val);
            if (l2r) {
                if (cur.left != null) {
                    next.push(cur.left);
                }
                if (cur.right != null) {
                    next.push(cur.right);
                }
            } else {
                if (cur.right != null) {
                    next.push(cur.right);
                }
                if (cur.left != null) {
                    next.push(cur.left);
                }
            }
            if (row.isEmpty()) {
                tmp = row;
                row = next;
                next = tmp;
                l2r = !l2r;
                ans.add(rowVals);
                rowVals = new ArrayList<>();
            }
        }
        return ans;
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
        List<List<Integer>> ans = zigzagLevelOrder(r);
        System.out.println(Arrays.toString(ans.toArray()));
    }
}
//     ____6_____
//    /          \
//    2          10
//   / \        /
//  1   4      8
//     / \    / \
//    3   5  7   9
