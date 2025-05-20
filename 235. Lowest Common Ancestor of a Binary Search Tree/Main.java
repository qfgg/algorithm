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
    private int findLCA(TreeNode root, TreeNode p, TreeNode q, TreeNode[] res) {
        int lflag = 0, rflag = 0, flag = 0;
        if (root.val == p.val) {
            flag = 2;
        }
        if (root.val == q.val) {
            flag = 1;
        }
        if (root.left != null) {
            lflag = findLCA(root.left, p, q, res);
        }
        if (root.right != null) {
            rflag = findLCA(root.right, p, q, res);
        }
        flag = flag | lflag | rflag;
        if (flag == 3 && res[0] == null) {
            res[0] = root;
        }
        return flag;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode[] res = new TreeNode[1];
        findLCA(root, p, q, res);
        return res[0];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        TreeNode res = s.lowestCommonAncestor(root, root.left, root.left.right);
        System.out.println(res.val);
    }
}
