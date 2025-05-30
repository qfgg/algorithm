import java.util.*;


class Solution {
    Node cloneR (Node node, HashMap<Node, Node> visited) {
        if (node == null) {
            return null;
        }
        Node clone = new Node(node.val), next;
        visited.put(node, clone);
        List<Node> nbs = node.neighbors;
        for (Node nb : nbs) {
            if (visited.containsKey(nb)) {
                next = visited.get(nb);
            } else {
                next = cloneR(nb, visited);
            }
            clone.neighbors.add(next);
        }
        return clone;
    }
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        HashMap<Node, Node> visited = new HashMap<>();
        return cloneR(node, visited);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        Node node = null;
        Node res = s.cloneGraph(node);
        System.out.println();
    }
}
