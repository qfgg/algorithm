import java.util.*;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Main {
    public static void dfs(TreeNode root, int s, int d, List<Character> tmp, List<Character> sPath, List<Character> dPath) {
        if (root.val == s) {
            sPath.addAll(tmp);
        }
        if (root.val == d) {
            dPath.addAll(tmp);
        }
        if (root.left != null) {
            tmp.add('L');
            dfs(root.left, s, d, tmp, sPath, dPath);
            tmp.remove(tmp.size() - 1);
        }
        if (root.right != null) {
            tmp.add('R');
            dfs(root.right, s, d, tmp, sPath, dPath);
            tmp.remove(tmp.size() - 1);
        }
    }
    public static String getDirections(TreeNode root, int startValue, int destValue) {
        List<Character> sPath = new ArrayList<>();
        List<Character> dPath = new ArrayList<>();
        List<Character> tmp = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(root, startValue, destValue, tmp, sPath, dPath);
        while (!sPath.isEmpty() && !dPath.isEmpty()) {
            if (sPath.get(0) == dPath.get(0)) {
                sPath.remove(0);
                dPath.remove(0);
            } else {
                break;
            }
        }
        int s = sPath.size(), i;
        while (s > 0) {
            sb.append('U');
            s--;
        }
        s = dPath.size();
        i = 0;
        while (i < s) {
            sb.append(dPath.get(i));
            i++;
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        TreeNode r = new TreeNode(3);
        r.left = new TreeNode(5);
        r.right = new TreeNode(1);
        r.left.left = new TreeNode(6);
        r.left.left.left = new TreeNode(9);
        r.left.right = new TreeNode(2);
        r.left.right.left = new TreeNode(7);
        r.left.right.right = new TreeNode(4);
        r.right.left = new TreeNode(0);
        r.right.right = new TreeNode(8);
        String ans = getDirections(r, 9, 7);
        System.out.println(ans);
    }
}
//      ____3_____
//     /          \
//     5           1
//    / \         / \
//   6   2       0   8
//  /   / \
// 9   7   4