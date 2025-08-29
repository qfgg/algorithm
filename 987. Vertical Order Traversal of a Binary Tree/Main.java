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
    void computeRowCol(TreeNode root, int row, int col, List<List<Integer>> nodes) {
        List<Integer> node = new ArrayList<>(List.of(col, row, root.val));
        nodes.add(node);
        if (root.left != null) {
            computeRowCol(root.left, row + 1, col - 1, nodes);
        }
        if (root.right != null) {
            computeRowCol(root.right, row + 1, col + 1, nodes);
        }
    }
    List<List<Integer>> sortMerge(List<List<Integer>> nodes) {
        Collections.sort(nodes, (a, b) -> {
            int ca = a.get(0), ra = a.get(1), va = a.get(2), cb = b.get(0), rb = b.get(1), vb = b.get(2);
            if (ca == cb && ra == rb) {
                return va - vb;
            }
            if (ca == cb) {
                return ra - rb;
            }
            return ca - cb;
        });
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur, pre = nodes.get(0), toSave = new ArrayList<>(List.of(pre.get(2)));
        int i, n = nodes.size();
        for (i = 1; i < n; i++) {
            cur = nodes.get(i);
            if (cur.get(0) == pre.get(0)) {
                toSave.add(cur.get(2));
                if (i == n - 1) {
                    res.add(toSave);
                }
            } else {
                res.add(toSave);
                pre = cur;
                toSave = new ArrayList<>(List.of(pre.get(2)));
                if (i == n - 1) {
                    res.add(new ArrayList<>(List.of(cur.get(2))));
                }
            }
        }
        return res;
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> nodes = new ArrayList<>();
        computeRowCol(root, 0, 0, nodes);
        List<List<Integer>> res = sortMerge(nodes);
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        List<List<Integer>> res = s.verticalTraversal(root);
        System.out.println(res);
    }
}
