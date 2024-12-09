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
class Solution {
    public TreeNode build(int[] preorder, int s1, int e1, int[] postorder, int s2, int e2) {
        TreeNode root = new TreeNode(preorder[s1]);
        int i;
        for (i = s2; i < e2 - 1; i++) {
            if (postorder[i] == preorder[s1 + 1]) {
                break;
            }
        }
        if (s1 + 1 < e1) {
            root.left = build(preorder, s1 + 1, s1 + 2 + i - s2, postorder, s2, i + 1);
        }
        if (s1 + 2 + i - s2 < e1) {
            root.right = build(preorder, s1 + 2 + i - s2, e1, postorder, i + 1, e2 - 1);
        }
        return root;
    }
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return build(preorder, 0, preorder.length, postorder, 0, postorder.length);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] preorder = new int[]{2,1}, postorder = new int[]{1,2};
        TreeNode res = s.constructFromPrePost(preorder, postorder);
        System.out.println(res);
    }
}
//     ____1_____
//    /          \
//    2          3
//   / \        / \
//  4   5      6   7
