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
    private void calTree(TreeNode root, HashMap<Integer, Integer> heights, HashMap<Integer, TreeNode> parents) {
        int lh = 0, rh = 0;
        if (root.left != null) {
            calTree(root.left, heights, parents);
            parents.put(root.left.val, root);
            lh = heights.get(root.left.val);
        }
        if (root.right != null) {
            calTree(root.right, heights, parents);
            parents.put(root.right.val, root);
            rh = heights.get(root.right.val);
        }
        if (root.left == null && root.right == null) {
            heights.put(root.val, 0);
        } else {
            heights.put(root.val, Math.max(lh, rh) + 1);
        }
    }
    private int query(int target, HashMap<Integer, Integer> heights, HashMap<Integer, TreeNode> parents, int rootHeight) {
        int cur = target;
        int h = 0;
        TreeNode parent, sibling;
        while (parents.containsKey(cur)) {
            parent = parents.get(cur);
            sibling = parent.left == null ? null : parent.left.val == cur ? parent.right : parent.left;
            if (sibling != null) {
                if (heights.get(cur) <= heights.get(sibling.val)) {
                    return rootHeight;
                }
                h = h > heights.get(sibling.val) ? h + 1 : heights.get(sibling.val) + 1;
            } else if (cur != target) {
                h++;
            }
            cur = parent.val;
        }
        return h;
    }
    public int[] treeQueries(TreeNode root, int[] queries) {
        HashMap<Integer, Integer> heights = new HashMap<>();
        HashMap<Integer, TreeNode> parents = new HashMap<>();
        HashMap<Integer, Integer> memo = new HashMap<>();
        calTree(root, heights, parents);
        int[] ret = new int[queries.length];
        int i = 0, rootHeight = heights.get(root.val);
        for (int q : queries) {
            if (memo.containsKey(q)) {
                ret[i] = memo.get(q);
            } else {
                ret[i] = query(q, heights, parents, rootHeight);
                memo.put(q, ret[i]);
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
