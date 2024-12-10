import java.util.*;


class MyCircularQueue {
    int capacity;
    int size = 0;
    int front;
    int rear;
    int[] store;
    public MyCircularQueue(int k) {
        capacity = k;
        store = new int[k];
    }

    public boolean enQueue(int value) {
        if (size == capacity) {
            return false;
        }
        if (size == 0) {
            front = 0;
            rear = 0;
        } else {
            if (rear == capacity - 1) {
                rear = 0;
            } else {
                rear++;
            }
        }
        store[rear] = value;
        size++;
        return true;
    }

    public boolean deQueue() {
        if (size == 0) {
            return false;
        }
        if (front == capacity - 1) {
            front = 0;
        } else {
            front++;
        }
        size--;
        return true;
    }

    public int Front() {
        if (size == 0) {
            return -1;
        }
        return store[front];
    }

    public int Rear() {
        if (size == 0) {
            return -1;
        }
        return store[rear];
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
        MyCircularQueue myCircularQueue = new MyCircularQueue(2);
        System.out.println(myCircularQueue.enQueue(1)); // return True
        System.out.println(myCircularQueue.enQueue(2)); // return True
        System.out.println(myCircularQueue.deQueue()); // return True
        System.out.println(myCircularQueue.enQueue(3)); // return True
        System.out.println(myCircularQueue.deQueue()); // return True
        System.out.println(myCircularQueue.enQueue(3)); // return True
        System.out.println(myCircularQueue.deQueue()); // return True
        System.out.println(myCircularQueue.enQueue(3)); // return True
        System.out.println(myCircularQueue.Front());     // return 3
    }
}
