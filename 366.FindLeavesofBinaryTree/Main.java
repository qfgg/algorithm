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
public class Main {
    public static int dfs(TreeNode root, Map<Integer, List<Integer>> map) {
        if (root == null) {
            return 0;
        }
        int leftLvl, rightLvl, rLvl;
        leftLvl = dfs(root.left, map);
        rightLvl = dfs(root.right, map);
        rLvl = Math.max(leftLvl, rightLvl) + 1;
        if (map.containsKey(rLvl)) {
            map.get(rLvl).add(root.val);
        } else {
            map.put(rLvl, new ArrayList<>(Arrays.asList(root.val)));
        }
        return rLvl;
    }
    public static List<List<Integer>> findLeaves(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        dfs(root, map);
        return new ArrayList<>(map.values());
    }
    public static void main(String[] args) {
        TreeNode r = new TreeNode(6);
        r.left = new TreeNode(2);
        r.right = new TreeNode(10);
        r.left.left = new TreeNode(1);
        r.left.right = new TreeNode(4);
        r.left.right.left = new TreeNode(3);
        r.left.right.right = new TreeNode(5);
        r.right.left = new TreeNode(8);
        r.right.left.left = new TreeNode(7);
        r.right.left.right = new TreeNode(9);
        List<List<Integer>> ans = findLeaves(r);
        System.out.println(Arrays.toString(ans.toArray()));
    }
}
//     ____6_____
//    /          \
//    2          10
//   / \        /
//  1   4      8
//     / \    / \
//    3   5  7   9
