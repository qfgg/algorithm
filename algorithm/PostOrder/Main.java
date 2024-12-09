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

class Main {
    public static List<Integer> PostOrder(TreeNode root) {
        List<Integer> path = new ArrayList<>();
        Stack<TreeNode> nodes = new Stack<>();
        TreeNode cur = root, pre = null;
        while (true) {
            while (cur != null) {
                nodes.push(cur);
                cur = cur.left;
            }
            cur = nodes.peek();
            while (cur != null && (cur.right == null || cur.right == pre)) {
                pre = nodes.pop();
                path.add(pre.val);
                if (nodes.isEmpty()) {
                    return path;
                }
                cur = nodes.peek();
            }
            cur = cur.right;
        }
    }

    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(1);
        r1.right = new TreeNode(2);
        r1.right.left = new TreeNode(3);
        r1.right.right = new TreeNode(4);
        List<Integer> ret1 = PostOrder(r1);
        System.out.println(Arrays.toString(ret1.toArray()));

        TreeNode r2 = new TreeNode(1);
        r2.left = new TreeNode(2);
        r2.right = new TreeNode(3);
        r2.left.left = new TreeNode(4);
        r2.left.right = new TreeNode(5);
        r2.left.right.left = new TreeNode(7);
        r2.left.right.right = new TreeNode(8);
        r2.right.left = new TreeNode(6);
        r2.right.left.left = new TreeNode(9);
        r2.right.left.right = new TreeNode(10);
        List<Integer> ret2 = PostOrder(r2);
        System.out.println(Arrays.toString(ret2.toArray()));
    }
}
//   1
//    \
//    2
//    / \
//    3   4

//     ____1_____
//    /          \
//    2            3
//   / \          /
//   4  5        6
//      / \    / \
//     7   8  9  10