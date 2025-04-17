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
    private void calTree(TreeNode root, int[] heights, int[] parents, int[] siblings) {
        int lh = 0, rh = 0;
        if (root.left != null) {
            calTree(root.left, heights, parents, siblings);
            parents[root.left.val] = root.val;
            lh = heights[root.left.val];
        }
        if (root.right != null) {
            calTree(root.right, heights, parents, siblings);
            parents[root.right.val] = root.val;
            rh = heights[root.right.val];
        }
        if (root.left == null && root.right == null) {
            heights[root.val] = 0;
        } else {
            heights[root.val] = Math.max(lh, rh) + 1;
            if (root.left != null && root.right != null) {
                siblings[root.left.val] = root.right.val;
                siblings[root.right.val] = root.left.val;
            }
        }
    }
    private int query(int target, int[] heights, int[] parents, int[] siblings, int rootHeight) {
        int cur = target;
        int h = 0;
        int parent, sibling;
        while (parents[cur] != 0) {
            parent = parents[cur];
            sibling = siblings[cur];
            if (sibling != 0) {
                if (heights[cur] <= heights[sibling]) {
                    return rootHeight;
                }
                h = h > heights[sibling] ? h + 1 : heights[sibling] + 1;
            } else if (cur != target) {
                h++;
            }
            cur = parent;
        }
        return h;
    }
    public int[] treeQueries(TreeNode root, int[] queries) {
        int[] heights = new int[100001];
        int[] parents = new int[100001];
        int[] siblings = new int[100001];
        int[] memo = new int[100001];
        Arrays.fill(memo, -1);
        calTree(root, heights, parents, siblings);
        int[] ret = new int[queries.length];
        int i = 0, rootHeight = heights[root.val];
        for (int q : queries) {
            if (memo[q] > -1) {
                ret[i] = memo[q];
            } else {
                memo[q] = query(q, heights, parents, siblings, rootHeight);
                ret[i] = memo[q];
            }
            i++;
        }
        return ret;
    }
}
public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(5);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(3);
        Solution s = new Solution();
        int[] res = s.treeQueries(root, new int[]{3});
        System.out.println(Arrays.toString(res));
    }
}
