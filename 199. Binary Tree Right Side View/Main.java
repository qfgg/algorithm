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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        TreeNode cur;
        int levelSize = q.size();
        boolean levelDone = false;
        while (levelSize != 0) {
            cur = q.poll();
            levelSize--;
            if (!levelDone) {
                res.add(cur.val);
                levelDone = true;
            }
            if (cur.right != null) {
                q.add(cur.right);
            }
            if (cur.left != null) {
                q.add(cur.left);
            }
            if (levelSize == 0) {
                levelSize = q.size();
                levelDone = false;
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
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);
        List<Integer> res = s.rightSideView(root);
        System.out.println(res);
    }
}
