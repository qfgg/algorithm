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
    public static void getLeft(TreeNode root, boolean isRoot, List<Integer> l) {
        l.add(root.val);
        if ((isRoot && root.left == null) ||
                (root.left == null && root.right == null)) {
            return;
        }

        if (root.left != null) {
            getLeft(root.left, false, l);
        } else {
            getLeft(root.right, false, l);
        }
    }
    public static void getLeaves(TreeNode root, List<Integer> lf) {
        if (root.left == null && root.right == null) {
            lf.add(root.val);
            return;
        }
        if (root.left != null) {
            getLeaves(root.left, lf);
        }
        if (root.right != null) {
            getLeaves(root.right, lf);
        }
    }
    public static void getRight(TreeNode root, boolean isRoot, List<Integer> r) {
        if ((isRoot && root.right == null) ||
                (root.left == null && root.right == null)) {
            r.add(root.val);
            return;
        }

        if (root.right != null) {
            getRight(root.right, false, r);
        } else {
            getRight(root.left, false, r);
        }
        r.add(root.val);
    }
    public static List<Integer> BinaryTreeBoundary(TreeNode root) {
        if (root.left == null && root.right == null) {
            return new ArrayList<>(Arrays.asList(root.val));
        }
        List<Integer> l = new ArrayList<>();
        getLeft(root, true, l);
        List<Integer> lf = new ArrayList<>();
        getLeaves(root, lf);
        List<Integer> r = new ArrayList<>();
        getRight(root, true, r);
        if (l.size() > 1) {
            l.remove(l.size() - 1);
        }
        r.remove(r.size() - 1);
        if (r.size() > 0) {
            r.remove(0);
        }
        List<Integer> ret = new ArrayList<>();
        ret.addAll(l);
        ret.addAll(lf);
        ret.addAll(r);
        return ret;
    }

    public static void main(String[] args) {
        TreeNode r = new TreeNode(1);
        r.left = new TreeNode(2);
        r.right = new TreeNode(3);
        r.left.left = new TreeNode(4);
        r.left.right = new TreeNode(5);
        r.left.right.left = new TreeNode(7);
        r.left.right.right = new TreeNode(8);
        r.right.left = new TreeNode(6);
        r.right.left.left = new TreeNode(9);
        r.right.left.right = new TreeNode(10);
        List<Integer> ret1 = BinaryTreeBoundary(r);
        System.out.println(Arrays.toString(ret1.toArray()));
        r = new TreeNode(1);
        r.right = new TreeNode(2);
        r.right.left = new TreeNode(3);
        r.right.right = new TreeNode(4);
        List<Integer> ret2 = BinaryTreeBoundary(r);
        System.out.println(Arrays.toString(ret2.toArray()));
    }
}
