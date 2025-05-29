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
    private int dfs(TreeNode root, int k, PriorityQueue<Integer> heap) {
        if (root.left == null && root.right == null) {
            heap.add(1);
            if (heap.size() > k) {
                heap.poll();
            }
            return 1;
        }
        int lsize = 0, rsize = 0, size;
        if (root.left != null) {
            lsize = dfs(root.left, k, heap);
        }
        if (root.right != null) {
            rsize = dfs(root.right, k, heap);
        }
        if (lsize > 0 && lsize == rsize) {
            size = lsize + rsize + 1;
            heap.add(size);
            if (heap.size() > k) {
                heap.poll();
            }
            return size;
        }
        return 0;
    }
    public int kthLargestPerfectSubtree(TreeNode root, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        dfs(root, k, heap);
        return heap.isEmpty() || heap.size() < k ? -1 : heap.peek();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(14);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(15);
        int res = s.kthLargestPerfectSubtree(root, 2);
        System.out.println(res);
    }
}
