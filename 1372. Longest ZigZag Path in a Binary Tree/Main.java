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
    int max = 0;
    int[] dfs(TreeNode root){
        if (root.left == null && root.right == null) {
            return new int[]{0, 0};
        }
        int[] lmax = null, rmax = null, rootmax = new int[2];
        if (root.left != null) {
            lmax = dfs(root.left);
            rootmax[0] = lmax[1] + 1;
        }
        if (root.right != null) {
            rmax = dfs(root.right);
            rootmax[1] = rmax[0] + 1;
        }
        max = Math.max(Math.max(max, rootmax[0]), rootmax[1]);
        return rootmax;
    }
    public int longestZigZag(TreeNode root) {
        dfs(root);
        return max;
    }
}
