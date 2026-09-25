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
class CBTInserter {
    private TreeNode croot;
    private Queue<TreeNode> parents;

    public CBTInserter(TreeNode root) {
        croot = root;
        parents =  new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        TreeNode cur;
        while (!q.isEmpty()) {
            cur = q.poll();
            if (cur.left == null || cur.right == null) {
                parents.add(cur);
            }
            if (cur.left != null) {
                q.add(cur.left);
            }
            if (cur.right != null) {
                q.add(cur.right);
            }
        }
    }

    public int insert(int val) {
        TreeNode head = parents.peek();
        TreeNode add = new TreeNode(val);
        if (head.left == null) {
            head.left = add;
        } else {
            head.right = add;
            parents.poll();
        }
        parents.add(add);
        return head.val;
    }

    public TreeNode get_root() {
        return croot;
    }
}
public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        CBTInserter cBTInserter = new CBTInserter(root);
        cBTInserter.insert(3);  // return 1
        cBTInserter.insert(4);  // return 2
        cBTInserter.get_root(); // return [1, 2, 3, 4]
    }
}
