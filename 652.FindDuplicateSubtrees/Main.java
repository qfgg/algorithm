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
    public static String dfs(TreeNode root, Map<String, Integer> treeKeyCnt, StringBuilder sb, List<TreeNode> res) {
        String lKey = "", rKey = "";
        if (root.left != null) {
            lKey = dfs(root.left, treeKeyCnt, sb, res);
        }
        if (root.right != null) {
            rKey = dfs(root.right, treeKeyCnt, sb, res);
        }
        if (!lKey.isEmpty()) {
            sb.append(lKey);
            sb.append("^");
        }
        sb.append(root.val);
        if (!rKey.isEmpty()) {
            sb.append("$");
            sb.append(rKey);
        }
        String key = sb.toString();
        sb.setLength(0);
        if (!treeKeyCnt.containsKey(key)) {
            treeKeyCnt.put(key, 1);
        } else if (treeKeyCnt.get(key) == 1) {
            res.add(root);
            treeKeyCnt.put(key, 2);
        }
        return key;
    }
    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        Map<String, Integer> treeKeyCnt = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        dfs(root, treeKeyCnt, sb, res);
        return res;
    }
    public static void main(String[] args) {
        TreeNode r = new TreeNode(0);
        r.left = new TreeNode(0);
        r.right = new TreeNode(0);
        r.left.left = new TreeNode(0);
        r.right.right = new TreeNode(0);
        r.right.right.right = new TreeNode(0);
        List<TreeNode> ans = findDuplicateSubtrees(r);
        System.out.println(Arrays.toString(ans.toArray()));
    }
}
//     ____0_____
//    /          \
//   0            0
//  /              \
// 0                0
//                   \
//                    0