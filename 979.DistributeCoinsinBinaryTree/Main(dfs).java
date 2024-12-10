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
    public static int dfs(TreeNode n, int level, Queue<Integer> levels, int[] moves) {
        if (n == null) {
            return 0;
        }
//        if subtree has more coins, need to move to current node
        int rest = 0;
        Queue<Integer> lLevels = null;
        Queue<Integer> rLevels = null;
        if (n.left != null) {
            lLevels = new LinkedList<>();
            rest += dfs(n.left, level + 1, lLevels, moves);
        }
        if (n.right != null) {
            rLevels = new LinkedList<>();
            rest += dfs(n.right,level + 1, rLevels, moves);
        }
        moves[0] += rest;
        n.val += rest;
        while (lLevels != null && !lLevels.isEmpty()) {
            levels.add(lLevels.poll());
        }
        while (rLevels != null && !rLevels.isEmpty()) {
            levels.add(rLevels.poll());
        }
        if (n.val == 0) {
//            add new entry to '0's levels queue
            levels.add(level);
            n.val = 1;
        } else if (n.val > 1) {
//            distribute coins to '0's using levels queue
            int numOfSpace = levels.size();
            int available = n.val - 1;
            int numUsed = Math.min(numOfSpace, available);
            n.val -= numUsed;
            int i = numUsed;
            while (i > 0) {
                moves[0] += levels.poll() - level;
                i--;
            }
            if (available > numUsed) {
//                return unused coins num to parent
                return available - numUsed;
            }
        }
        return 0;
    }
    public static int distributeCoins(TreeNode root) {
        Queue<Integer> distance = new LinkedList<>();
        int[] moves = new int[1];
        dfs(root, 0, distance, moves);
        return moves[0];
    }
    public static void main(String[] args) {
        TreeNode r = new TreeNode(0);
        r.left = new TreeNode(0);
        r.right = new TreeNode(0);
        r.left.left = new TreeNode(0);
        r.left.left.right = new TreeNode(0);
        r.right.left = new TreeNode(8);
        r.right.right = new TreeNode(2);
        r.right.right.left = new TreeNode(0);
        r.right.right.right = new TreeNode(0);
        r.right.right.right.right = new TreeNode(0);
        int ret = distributeCoins(r);
        System.out.println(ret);
    }
}
