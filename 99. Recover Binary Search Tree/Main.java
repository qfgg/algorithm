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
    TreeNode[] dfs(TreeNode root, TreeNode[] res) {
        TreeNode[] ret = new TreeNode[2];
        if (root.left == null && root.right == null) {
            ret[0] = root;
            ret[1] = root;
            return ret;
        }
        TreeNode[] leftMinMax = null, rightMinMax = null;
        TreeNode minNode = root, maxNode = root;
        if (root.left != null) {
            leftMinMax = dfs(root.left, res);
            if (leftMinMax[0].val < minNode.val) {
                minNode = leftMinMax[0];
            }
            if (leftMinMax[1].val > maxNode.val) {
                maxNode = leftMinMax[1];
            }
        }
        if (root.right != null) {
            rightMinMax = dfs(root.right, res);
            if (rightMinMax[0].val < minNode.val) {
                minNode = rightMinMax[0];
            }
            if (rightMinMax[1].val > maxNode.val) {
                maxNode = rightMinMax[1];
            }
        }
        ret[0] = minNode;
        ret[1] = maxNode;
        if (leftMinMax != null && root.val < leftMinMax[1].val) {
            if (res[0] == null || root.val < res[0].val) {
                res[0] = root;
            }
            if (res[1] == null || leftMinMax[1].val > res[1].val) {
                res[1] = leftMinMax[1];
            }
        }
        if (rightMinMax != null && root.val > rightMinMax[0].val) {
            if (res[0] == null || rightMinMax[0].val < res[0].val) {
                res[0] = rightMinMax[0];
            }
            if (res[1] == null || root.val > res[1].val) {
                res[1] = root;
            }
        }
        return ret;
    }
    public void recoverTree(TreeNode root) {
        TreeNode[] res = new TreeNode[2];
        dfs(root, res);
        int tmp = res[0].val;
        res[0].val = res[1].val;
        res[1].val = tmp;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        s.recoverTree(root);
        System.out.println();
    }
}
