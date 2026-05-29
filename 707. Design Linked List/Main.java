import java.util.*;

class Node {
    int value;
    Node pre, next;
    public Node(int val) {
        value = val;
    }
}
class MyLinkedList {
    Node head, tail;
    public MyLinkedList() {
        head = null;
        tail = null;
    }
    public int get(int index) {
        Node cur = head;
        int idx = 0;
        while (cur != null) {
            if (idx == index) {
                return cur.value;
            }
            idx++;
            cur = cur.next;
        }
        return -1;
    }
    public void addAtHead(int val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            head.pre = newNode;
            newNode.next = head;
            head = newNode;
        }
    }
    public void addAtTail(int val) {
        Node newNode = new Node(val);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.pre = tail;
            tail = newNode;
        }
    }
    public void addAtIndex(int index, int val) {
        if (index == 0) {
            addAtHead(val);
            return;
        }
        int idx;
        Node pre = head, cur, newNode = new Node(val);
        if (head == null) {
            cur = null;
            idx = 0;
        } else {
            cur = head.next;
            idx = 1;
        }
        while (cur != null) {
            if (idx == index) {
                newNode.pre = pre;
                newNode.next = cur;
                pre.next = newNode;
                cur.pre = newNode;
                return;
            }
            idx++;
            pre = pre.next;
            cur = cur.next;
        }
        if (index == idx) {
            addAtTail(val);
        }
    }
    public void deleteAtIndex(int index) {
        if (tail == null) {
            return;
        }
        if (0 == index) {
            head = head.next;
            if (head != null) {
                head.pre = null;
            }
            return;
        }
        Node pre = head, cur = head.next;
        int idx = 1;
        while (cur != null) {
            if (idx == index) {
                pre.next = cur.next;
                if (cur.next != null) {
                    cur.next.pre = pre;
                }
                if (cur == tail) {
                    tail = cur.pre;
                }
                return;
            }
            idx++;
            pre = pre.next;
            cur = cur.next;
        }
    }
}
public class Main {
    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtIndex(1, 0);
        System.out.println(myLinkedList.get(0));
    }
}
