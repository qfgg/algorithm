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
    private void getHeight(TreeNode root, int[] heights) {
        int lh = 0, rh = 0;
        if (root.left == null && root.right == null) {
            heights[root.val] = 0;
            return;
        }
        if (root.left != null) {
            getHeight(root.left, heights);
            lh = heights[root.left.val];
        }
        if (root.right != null) {
            getHeight(root.right, heights);
            rh = heights[root.right.val];
        }
        heights[root.val] = Math.max(lh, rh) + 1;
    }
    private void getHeightWithoutRoot(TreeNode root, int[] heights, int[] removeHeights, int realRootHeight, int depth) {
        int lh = root.left == null ? -1 : heights[root.left.val];
        int rh = root.right == null ? -1 : heights[root.right.val];
        int candidateHeight;
        if (removeHeights[root.val] == realRootHeight) {
            if (root.left != null) {
                removeHeights[root.left.val] = realRootHeight;
                getHeightWithoutRoot(root.left, heights, removeHeights, realRootHeight, depth + 1);
            }
            if (root.right != null) {
                removeHeights[root.right.val] = realRootHeight;
                getHeightWithoutRoot(root.right, heights, removeHeights, realRootHeight, depth + 1);
            }
        } else if (lh == rh) {
            if (root.left != null) {
                removeHeights[root.left.val] = realRootHeight;
                getHeightWithoutRoot(root.left, heights, removeHeights, realRootHeight, depth + 1);
            }
            if (root.right != null) {
                removeHeights[root.right.val] = realRootHeight;
                getHeightWithoutRoot(root.right, heights, removeHeights, realRootHeight, depth + 1);
            }
        } else if (lh < rh) {
            if (root.left != null) {
                removeHeights[root.left.val] = realRootHeight;
                getHeightWithoutRoot(root.left, heights, removeHeights, realRootHeight, depth + 1);
            }
            candidateHeight = root.left == null ? depth : depth + heights[root.left.val] + 1;
            removeHeights[root.right.val] = Math.max(removeHeights[root.val], candidateHeight);
            getHeightWithoutRoot(root.right, heights, removeHeights, realRootHeight, depth + 1);
        } else {
            if (root.right != null) {
                removeHeights[root.right.val] = realRootHeight;
                getHeightWithoutRoot(root.right, heights, removeHeights, realRootHeight, depth + 1);
            }
            candidateHeight = root.right == null ? depth : depth + heights[root.right.val] + 1;
            removeHeights[root.left.val] = Math.max(removeHeights[root.val], candidateHeight);
            getHeightWithoutRoot(root.left, heights, removeHeights, realRootHeight, depth + 1);
        }
    }
    public int[] treeQueries(TreeNode root, int[] queries) {
        int[] heights = new int[100001];
        int[] removeHeights = new int[100001];
        getHeight(root, heights);
        getHeightWithoutRoot(root, heights, removeHeights, heights[root.val], 0);
        int[] ret = new int[queries.length];
        int i = 0;
        for (int q : queries) {
            ret[i] = removeHeights[q];
            i++;
        }
        return ret;
    }
}
public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        Solution s = new Solution();
        int[] res = s.treeQueries(root, new int[]{3});
        System.out.println(Arrays.toString(res));
    }
}
