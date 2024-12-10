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

class Tree {
    TreeNode root;
    Tree() {}
    Tree(int[] vals) {
        this.root = buildTree(vals, 0);
    }
    TreeNode buildTree(int[] vals, int idx) {
        TreeNode node = null;
        if (vals[idx] >= 0) {
            node = new TreeNode(vals[idx]);
            int left = 2 * idx  + 1;
            int right = left + 1;
            int len = vals.length;
            if (left < len && vals[left] >= 0) {
                node.left = buildTree(vals, left);
            }
            if (right < len && vals[right] >= 0) {
                node.right = buildTree(vals, right);
            }
        }
        return node;
    }
}

class Main {
    public static int[] toFullTreeArray(TreeNode root) {
        if (root == null) {
            return new int[]{-1};
        }
        Queue<AbstractMap.SimpleEntry<Integer, TreeNode>> queue = new LinkedList<>();
        Queue<AbstractMap.SimpleEntry<Integer, TreeNode>> result = new LinkedList<>();
        int curIdx = 0;
        int maxIdx = 0;
        queue.add(new AbstractMap.SimpleEntry<>(curIdx, root));
        while (!queue.isEmpty()) {
            AbstractMap.SimpleEntry<Integer, TreeNode> cur = queue.poll();
            result.add(cur);
            curIdx = cur.getKey();
            TreeNode curNode = cur.getValue();
            if (curNode != null && curIdx > maxIdx) {
                maxIdx = curIdx;
            }
            int left = 2 * curIdx + 1;
            int right = left + 1;
            if (curNode.left != null) {
                queue.add(new AbstractMap.SimpleEntry<>(left, curNode.left));
            }
            if (curNode.right != null) {
                queue.add(new AbstractMap.SimpleEntry<>(right, curNode.right));
            }
        }
        int len = maxIdx + 1;
        int[] ret = new int[len];
        AbstractMap.SimpleEntry<Integer, TreeNode> pair = result.poll();
         for (int i = 0; i < len; i++) {
            if (pair == null || pair.getKey() == i) {
                TreeNode n = pair.getValue();
                ret[i] = n.val;
                pair = result.isEmpty() ? null : result.poll();
            } else {
                ret[i] = -1;
            }
        }
        return ret;
    }
    public static int sendDown(int[] nodes, int i) {
        int len = nodes.length;
        int count = 0;
        int curLevel = 0;
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> nextQ = new LinkedList<>();
        Queue<Integer> tmp;
        Stack<Integer[]> moves = new Stack<>();
        int l;
        int r;
        q.add(i);
        while (true) {
            int curIdx = q.poll();
            if (curLevel > 0 && nodes[curIdx] == 0) {
                Integer[] m = new Integer[]{curIdx, curLevel};
                moves.push(m);
            }
            l = 2 * curIdx + 1;
            r = l + 1;
            if (l < len && nodes[l] != -1) {
                nextQ.add(l);
            }
            if (r < len && nodes[r] != -1) {
                nextQ.add(r);
            }
            if (q.isEmpty() && nextQ.isEmpty()) {
                break;
            }
            if (q.isEmpty()) {
                tmp = q;
                q = nextQ;
                nextQ = tmp;
                curLevel++;
            }
        }
        while (nodes[i] > 1 && !moves.isEmpty()) {
            Integer[] m = moves.pop();
            count += m[1];
            int j = m[0];
            int ll = 2 * j + 1;
            if ((ll >= len || nodes[ll] == -1) && (ll + 1 >= len || nodes[ll + 1] == -1)) {
                nodes[j] = -1;
            } else {
                nodes[j] = 1;
            }
            if (nodes[i] == 1) {
                break;
            }
            nodes[i]--;
        }
        return count;
    }
    public static int distributeCoins(TreeNode root) {
        int[] nodes = toFullTreeArray(root);
        int len = nodes.length;
        int i = len - 1;
        int count = 0;
        while (i >= 0) {
            if (nodes[i] > 1) {
                count += sendDown(nodes, i);
//                not enough space for all coins to sent down, so need
//                to move to its parent
                if (nodes[i] > 1) {
                    int rest = nodes[i] - 1;
                    nodes[(i - 1) / 2] += rest;
                    count += rest;
                    nodes[i] = -1;
                }
            } else if (nodes[i] == 1) {
//                if current node is 1, left and right child is null,
//                remove subtree to reduce future unnecessary visit
                int l = 2 * i + 1;
                if ((l >= len || nodes[l] == -1) && (l + 1 >= len || nodes[l + 1] == -1)) {
                    nodes[i] = -1;
                }
            }
            i--;
        }
        return count;
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
