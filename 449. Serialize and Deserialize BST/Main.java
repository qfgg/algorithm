import java.util.*;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        String res = String.valueOf(root.val);
        if (root.left != null) {
            res = "(" + serialize(root.left) + ")" + res;
        }
        if (root.right != null) {
            res = res + "(" + serialize(root.right) + ")";
        }
        return res;
    }

    TreeNode ds(char[] ch, int start, int end) {
        int i;
        int lcnt = 0;
        TreeNode res, lt = null, rt = null;
        for (i = start; i <= end; i++) {
            if (ch[i] == '(') {
                lcnt++;
            } else if (ch[i] == ')') {
                lcnt--;
            }
            if (lcnt == 0) {
                break;
            }
        }
        if (i > start) {
            lt = ds(ch, start + 1, i - 1);
            i++;
        }
        int val = 0;
        for (; i <= end && ch[i] != '('; i++) {
            val = val * 10 + (ch[i] - '0');
        }
        res = new TreeNode(val);
        if (i < end) {
            rt = ds(ch, i + 1, end - 1);
        }
        res.left = lt;
        res.right = rt;
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        char[] ch = data.toCharArray();
        return ds(ch, 0, ch.length - 1);
    }
}
public class Main {
    public static void main(String[] args) {
        // Your Codec object will be instantiated and called as such:
        Codec ser = new Codec();
        Codec deser = new Codec();
        TreeNode root = new TreeNode(0);
        String tree = ser.serialize(root);
        TreeNode ans = deser.deserialize(tree);
        System.out.println();
    }
}
