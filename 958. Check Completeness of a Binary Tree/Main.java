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
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        TreeNode cur;
        boolean isEnd = false;
        while (!q.isEmpty()) {
            cur = q.poll();
            if ((!isEnd && cur.left == null && cur.right != null) ||
                    (isEnd && (cur.left != null || cur.right != null))) {
                return false;
            }
            if (!isEnd && cur.right == null) {
                isEnd = true;
            }
            if (cur.left != null) {
                q.add(cur.left);
            }
            if (cur.right != null) {
                q.add(cur.right);
            }
        }
        return true;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      TreeNode root  = new TreeNode(1);
      root.left = new TreeNode(2);
      root.right = new TreeNode(3);
      root.left.left = new TreeNode(4);
      boolean ret = s.isCompleteTree(root);
      System.out.println(ret);
  }
}
