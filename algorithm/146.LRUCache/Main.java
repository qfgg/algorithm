import java.util.*;

class ListNode {
    int key;
    int val;
    ListNode child;
    ListNode parent;
    public ListNode(int k, int v) {
        key = k;
        val = v;
    }
}
class LRUCache {
    int size = 0;
    int maxSize;
    ListNode head;
    ListNode tail;
    HashMap<Integer, ListNode> nodeMap = new HashMap<>();
    public LRUCache(int capacity) {
        maxSize = capacity;
    }

    public int get(int key) {
        if (!nodeMap.containsKey(key)) {
            return -1;
        }
        ListNode node = nodeMap.get(key);
        if (node == head) {
            return node.val;
        }
        if (node == tail) {
            tail.parent.child = null;
            tail = tail.parent;
            node.child = head;
            head.parent = node;
            head = node;
            return node.val;
        }
        node.parent.child = node.child;
        node.child.parent = node.parent;
        node.child = head;
        head.parent = node;
        head = node;
        return node.val;
    }

    public void put(int key, int value) {
        ListNode node;
        if (nodeMap.containsKey(key)) {
            node = nodeMap.get(key);
            node.val = value;
            if (node != head) {
                if (node == tail) {
                    tail.parent.child = null;
                    tail = tail.parent;
                    node.child = head;
                    head.parent = node;
                    head = node;
                } else {
                    node.parent.child = node.child;
                    node.child.parent = node.parent;
                    node.child = head;
                    head.parent = node;
                    head = node;
                }
            }
        } else {
            node = new ListNode(key, value);
            nodeMap.put(key, node);
            if (size == 0) {
                head = node;
                tail = node;
                size++;
            } else if (size < maxSize) {
                node.child = head;
                head.parent = node;
                head = node;
                size++;
            } else {
                node.child = head;
                head.parent = node;
                head = node;
                nodeMap.remove(tail.key);
                tail.parent.child = null;
                tail = tail.parent;
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // return -1 (not found)
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));    // return 4
    }
}
