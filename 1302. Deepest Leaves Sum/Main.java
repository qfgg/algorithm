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
    public int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        int size = 1, sum = 0;
        q.add(root);
        TreeNode cur;
        while (size > 0) {
            cur = q.poll();
            sum += cur.val;
            size--;
            if (cur.left != null) {
                q.add(cur.left);
            }
            if (cur.right != null) {
                q.add(cur.right);
            }
            if (size == 0) {
                if (q.isEmpty()) {
                    break;
                }
                size = q.size();
                sum = 0;
            }
        }
        return sum;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        int res = s.deepestLeavesSum(root);
        System.out.println(res);
    }
}
