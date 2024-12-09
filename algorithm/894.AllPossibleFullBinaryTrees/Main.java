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
class Solution {
    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> res = new ArrayList<>();
        if (n % 2 == 0) {
            return res;
        }
        if (n == 1) {
            res.add(new TreeNode(0));
            return res;
        }
        if (n == 3) {
            res.add(new TreeNode(0, new TreeNode(0), new TreeNode(0)));
            return res;
        }
        for (int i = 1; i < n - 1; i = i + 2) {
            List<TreeNode> l1 = allPossibleFBT(n - i - 1);
            List<TreeNode> l2 = allPossibleFBT(i);
            for (TreeNode t1 : l1) {
                for (TreeNode t2 : l2) {
                    res.add(new TreeNode(0, t1, t2));
                }
            }
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<TreeNode> res = s.allPossibleFBT(19);
        System.out.println(res);
    }
}
