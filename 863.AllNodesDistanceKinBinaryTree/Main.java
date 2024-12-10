import java.util.*;


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class Main {
    public static void getKChildren(TreeNode root, int k, List<Integer> ans) {
        if (k == 0) {
            ans.add(root.val);
            return;
        }
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        TreeNode cur;
        int size = nodes.size(), dist = 0;
        while (size != 0) {
            cur = nodes.poll();
            size--;
            if (cur.left != null) {
                nodes.add(cur.left);
            }
            if (cur.right != null) {
                nodes.add(cur.right);
            }
            if (size == 0) {
                dist++;
                if (dist == k) {
                    break;
                }
                size = nodes.size();
            }
        }
        while (!nodes.isEmpty()) {
            ans.add(nodes.poll().val);
        }
    }
    public static int dfs(TreeNode root, TreeNode target, int k, List<Integer> ans) {
        if (root == target) {
            return 0;
        }
        int leftDist = -1, rightDist = -1;
        if (root.left != null) {
            leftDist = dfs(root.left, target, k, ans);
        }
        if (root.right != null) {
            rightDist = dfs(root.right, target, k, ans);
        }
        if (leftDist != -1) {
            if (k == leftDist + 1) {
                ans.add(root.val);
            } else if (k > leftDist + 1) {
                if (root.right != null) {
                    getKChildren(root.right, k - leftDist - 2, ans);
                }
            }
        } else if (rightDist != -1) {
            if (k == rightDist + 1) {
                ans.add(root.val);
            } else if (k > rightDist + 1) {
                if (root.left != null) {
                    getKChildren(root.left, k - rightDist - 2, ans);
                }
            }
        }
        return leftDist != -1 ?
                leftDist + 1 : rightDist != -1 ?
                rightDist + 1 : -1;
    }
    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        getKChildren(target, k, ans);
        dfs(root, target, k, ans);
        return ans;
    }
    public static void main(String[] args) {
        TreeNode r = new TreeNode(3);
        r.left = new TreeNode(5);
        r.right = new TreeNode(1);
        r.left.left = new TreeNode(6);
        r.left.right = new TreeNode(2);
        r.left.right.left = new TreeNode(7);
        r.left.right.right = new TreeNode(4);
        r.right.left = new TreeNode(0);
        r.right.right = new TreeNode(8);
        List<Integer> ans = distanceK(r, r.left, 2);
        System.out.println(Arrays.toString(ans.toArray()));
    }
}
//     ____3_____
//    /          \
//    5           1
//   / \         / \
//  6   2       0   8
//     / \
//    7   4
