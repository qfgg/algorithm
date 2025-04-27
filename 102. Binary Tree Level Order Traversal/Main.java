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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int size = 1, nextSize = 0;
        TreeNode cur;
        List<Integer> level = new ArrayList<>();
        while (size > 0) {
            cur = q.poll();
            level.add(cur.val);
            size--;
            if (cur.left != null) {
                q.add(cur.left);
                nextSize++;
            }
            if (cur.right != null) {
                q.add(cur.right);
                nextSize++;
            }
            if (size == 0) {
                size = nextSize;
                nextSize = 0;
                res.add(level);
                level = new ArrayList<>();
            }
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        List<List<Integer>> res = s.levelOrder(root);
        System.out.println(res);
    }
}
