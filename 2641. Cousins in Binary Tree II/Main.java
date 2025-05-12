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
    public TreeNode replaceValueInTree(TreeNode root) {
        root.val = 0;
        Queue<TreeNode> parent = new LinkedList<>();
        parent.add(root);
        int levelSum = 0, nextLevelSum = 0, curSum = 0;
        int levelSize = parent.size();
        if (root.left != null) {
            curSum += root.left.val;
        }
        if (root.right != null) {
            curSum += root.right.val;
        }
        if (root.left != null && root.right != null) {
            root.left.val = curSum;
            root.right.val = curSum;
        }
        levelSum += curSum;
        TreeNode cur;
        while (levelSize > 0) {
            cur = parent.poll();
            levelSize--;
            curSum = 0;
            if (cur.left != null) {
                cur.left.val = levelSum - cur.left.val;
                parent.add(cur.left);
                if (cur.left.left != null) {
                    curSum += cur.left.left.val;
                }
                if (cur.left.right != null) {
                    curSum += cur.left.right.val;
                }
                if (cur.left.left != null && cur.left.right != null) {
                    cur.left.left.val = curSum;
                    cur.left.right.val = curSum;
                }
                nextLevelSum += curSum;
            }
            curSum = 0;
            if (cur.right != null) {
                cur.right.val = levelSum - cur.right.val;
                parent.add(cur.right);
                if (cur.right.left != null) {
                    curSum += cur.right.left.val;
                }
                if (cur.right.right != null) {
                    curSum += cur.right.right.val;
                }
                if (cur.right.left != null && cur.right.right != null) {
                    cur.right.left.val = curSum;
                    cur.right.right.val = curSum;
                }
                nextLevelSum += curSum;
            }
            if (levelSize == 0) {
                levelSize = parent.size();
                levelSum = nextLevelSum;
                nextLevelSum = 0;
            }
        }
        return root;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(5);
//        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.right.right = new TreeNode(9);
//        root.left.left = new TreeNode(2);
//        root.left.right = new TreeNode(4);
        s.replaceValueInTree(root);
        System.out.println();
    }
}
