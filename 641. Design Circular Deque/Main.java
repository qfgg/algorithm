import java.util.*;


class MyCircularDeque {
    int[] cdq;
    int front, rear, size, capacity;

    public MyCircularDeque(int k) {
        cdq = new int[k];
        front = 0;
        rear = -1;
        capacity = k;
        size = 0;
    }

    public boolean insertFront(int value) {
        if (size == capacity) {
            return false;
        }
        if (size == 0) {
            rear = front;
        } else {
            front = front == 0 ? capacity - 1: front - 1;
        }
        cdq[front] = value;
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if (size == capacity) {
            return false;
        }
        if (size == 0) {
            rear = front;
        } else {
            rear = rear == capacity - 1 ? 0 : rear + 1;
        }
        cdq[rear] = value;
        size++;
        return true;
    }

    public boolean deleteFront() {
        if (size == 0) {
            return false;
        }
        if (size == 1) {
            front = 0;
            rear = -1;
        } else {
            front = front == capacity - 1 ? 0 : front + 1;
        }
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (size == 0) {
            return false;
        }
        if (size == 1) {
            front = 0;
            rear = -1;
        } else {
            rear = rear == 0 ? capacity - 1 : rear - 1;
        }
        size--;
        return true;
    }

    public int getFront() {
        if (size == 0) {
            return -1;
        }
        return cdq[front];
    }

    public int getRear() {
        if (size == 0) {
            return -1;
        }
        return cdq[rear];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}
public class Main {
    public static void main(String[] args) {
        MyCircularDeque myCircularDeque = new MyCircularDeque(5);
        System.out.println(myCircularDeque.insertFront(7));
        System.out.println(myCircularDeque.insertLast(0));
        System.out.println(myCircularDeque.getFront());
        System.out.println(myCircularDeque.insertFront(3));
        System.out.println(myCircularDeque.getFront());
        System.out.println(myCircularDeque.insertFront(9));
        System.out.println(myCircularDeque.getRear());
        System.out.println(myCircularDeque.getFront());
        System.out.println(myCircularDeque.deleteLast());
        System.out.println(myCircularDeque.getRear());
    }
}
