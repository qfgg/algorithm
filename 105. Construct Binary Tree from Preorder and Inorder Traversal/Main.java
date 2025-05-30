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
    TreeNode build(int[] preorder, int ps, int pe, int[] inorder, int is, int ie) {
        if (ps > pe) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[ps]);
        if (ps == pe) {
            return root;
        }
        HashSet<Integer> left = new HashSet<>();
        int i, n = preorder.length, inpos = 0;
        for (i = is; i <= ie; i++) {
            if (inorder[i] == preorder[ps]) {
                inpos = i;
                break;
            }
            left.add(inorder[i]);
        }
        for (i = ps + 1; i <= pe; i++) {
            if (!left.contains(preorder[i])) {
                break;
            }
        }
        i--;
        root.left = build(preorder, ps + 1, i, inorder, is, inpos - 1);
        root.right = build(preorder, i + 1, pe, inorder, inpos + 1, ie);
        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] preorder = new int[]{1,2};
        int[] inorder = new int[]{1,2};
        TreeNode res = s.buildTree(preorder, inorder);
        System.out.println(res);
    }
}
