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
    void dfs(TreeNode root, List<TreeNode> nodes) {
        if (root.left != null) {
            dfs(root.left, nodes);
        }
        nodes.add(root);
        if (root.right != null) {
            dfs(root.right, nodes);
        }
    }
    TreeNode buildBBST(List<TreeNode> nodes, int start, int end) {
        TreeNode ret;
        int mid;
        if (start == end) {
            return nodes.get(start);
        }
        mid = (start + end) / 2;
        ret = nodes.get(mid);
        ret.left = start == mid ? null : buildBBST(nodes, start, mid - 1);
        ret.right = mid == end ? null : buildBBST(nodes, mid + 1, end);
        return ret;
    }
    public TreeNode balanceBST(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        dfs(root, nodes);
        for (TreeNode node : nodes) {
            node.left = null;
            node.right = null;
        }
        return buildBBST(nodes, 0, nodes.size() - 1);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution slt = new Solution();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        TreeNode ret = slt.balanceBST(root);
        System.out.println(ret);
    }
}
