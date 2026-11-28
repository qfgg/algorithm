import java.util.*;


class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int size = q.size(), len, i;
        Node cur;
        List<Integer> row = new ArrayList<>();
        List<Node> ch;
        while (!q.isEmpty()) {
            cur = q.poll();
            row.add(cur.val);
            size--;
            ch = cur.children;
            len = ch.size();
            for (i = 0; i < len; i++) {
                q.add(ch.get(i));
            }
            if (size == 0) {
                ret.add(row);
                row = new ArrayList<>();
                size = q.size();
            }
        }
        return ret;
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      Node root = new Node(1);
      List<List<Integer>> res = s.levelOrder(root);
      System.out.println(res);
  }
}
