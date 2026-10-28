/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int dfs(TreeNode root, int presum){
        int lsum = 0, rsum = 0, sum;
        if (root.right!= null) {
            rsum = dfs(root.right, presum);
        }
        presum += rsum + root.val;
        if (root.left!= null) {
            lsum = dfs(root.left, presum);
        }
        sum = lsum + rsum + root.val;
        root.val = presum;
        return sum;
    }
    public TreeNode bstToGst(TreeNode root) {
        dfs(root, 0);
        return root;
    }
}
