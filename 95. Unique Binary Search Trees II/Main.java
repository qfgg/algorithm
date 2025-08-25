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
    List<TreeNode> gen(int n, int start) {
        if (n == 1) {
            return new ArrayList<>(List.of(new TreeNode(start)));
        }
        List<TreeNode> res = new ArrayList<>();
        for (int i = start; i < start + n; i++) {
            List<TreeNode> lTrees = i == start ? null : gen(i - start, start);
            List<TreeNode> rTrees = i == start + n - 1 ? null : gen(start + n - 1 - i, i + 1);
            if (lTrees == null) {
                for (TreeNode r : rTrees) {
                    TreeNode root = new TreeNode(i);
                    root.right = r;
                    res.add(root);
                }
            } else if (rTrees == null) {
                for (TreeNode l : lTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    res.add(root);
                }
            } else {
                for (TreeNode l : lTrees) {
                    for (TreeNode r : rTrees) {
                        TreeNode root = new TreeNode(i);
                        root.left = l;
                        root.right = r;
                        res.add(root);
                    }
                }
            }
        }
        return res;
    }
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = gen(n, 1);
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<TreeNode> res = s.generateTrees(3);
        System.out.println(res);
    }
}
