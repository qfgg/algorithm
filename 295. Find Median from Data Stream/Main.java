import java.util.*;

class MedianFinder {
    PriorityQueue<Integer> leftHeap;
    PriorityQueue<Integer> rightHeap;
    int m1;
    int m2;
    boolean isOdd;

    public MedianFinder() {
        leftHeap = new PriorityQueue<>((a,b) -> b-a);
        rightHeap = new PriorityQueue<>((a,b) -> a-b);
        m1 = Integer.MIN_VALUE;
        m2 = Integer.MIN_VALUE;
        isOdd = false;
    }

    public void addNum(int num) {
        if (m1 == Integer.MIN_VALUE && m2 == Integer.MIN_VALUE) {
            m1 = num;
        } else if (m2 == Integer.MIN_VALUE) {
            if (num >= m1) {
                m2 = num;
            } else {
                m2 = m1;
                m1 = num;
            }
        } else if (isOdd) {
            if (num == m1) {
                m2 = num;
            } else if (num > m1) {
                rightHeap.add(num);
                m2 = rightHeap.poll();
            } else {
                leftHeap.add(num);
                m2 = m1;
                m1 = leftHeap.poll();
            }
        } else {
            if (num >= m2) {
                leftHeap.add(m1);
                rightHeap.add(num);
                m1 = m2;
            } else if (num <= m1) {
                leftHeap.add(num);
                rightHeap.add(m2);
            } else {
                leftHeap.add(m1);
                rightHeap.add(m2);
                m1 = num;
            }
        }
        isOdd = !isOdd;
    }

    public double findMedian() {
        if (isOdd) {
            return m1;
        }
        return (double)(m1 + m2) / 2;
    }
}
public class Main {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(-1);    // arr = [-1]
        double res1 = medianFinder.findMedian(); // -1
        System.out.println(res1);
        medianFinder.addNum(-2);    // arr = [-2, -1]
        double res2 = medianFinder.findMedian(); // -1.5
        System.out.println(res2);
        medianFinder.addNum(-3);    // arr[-3, -2, -1]
        double res3 = medianFinder.findMedian(); // return -2.0
        System.out.println(res3);
        medianFinder.addNum(-4);    // arr[-4, -3, -2, -1]
        double res4 = medianFinder.findMedian(); // return -2.5
        System.out.println(res4);
        medianFinder.addNum(-5);    // arr[-5, -4, -3, -2, -1]
        double res5 = medianFinder.findMedian(); // return -3.0
        System.out.println(res5);
    }
}
