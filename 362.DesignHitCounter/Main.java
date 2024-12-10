


import java.util.*;

class HitCounter {
    Queue<int[]> dq = new LinkedList<>();
    int sum = 0;
    int curTime = 0;
    int cache = 0;
    public void hit(int timestamp) {
        int earliest;
        int[] front;
        if (timestamp == curTime) {
            cache++;
        } else {
            dq.add(new int[]{curTime, cache});
            curTime = timestamp;
            cache = 1;
            earliest = timestamp - 299;
            while (!dq.isEmpty()) {
                front = dq.peek();
                if (front[0] >= earliest) {
                    break;
                } else {
                    sum -= front[1];
                    dq.poll();
                }
            }
        }
        sum++;
    }
    public int getHits(int timestamp) {
        int earliest = timestamp - 299;
        int[] front;
        while (!dq.isEmpty()) {
            front = dq.peek();
            if (front[0] >= earliest) {
                break;
            } else {
                sum -= front[1];
                dq.poll();
            }
        }
        return sum;
    }
}
public class Main {
    public static void main(String[] args) {
        HitCounter counter = new HitCounter();
        // hit at timestamp 1.
        counter.hit(1);
        // hit at timestamp 2.
        counter.hit(2);
        // hit at timestamp 3.
        counter.hit(3);
        // get hits at timestamp 4, should return 3.
        System.out.println(counter.getHits(4));
        // hit at timestamp 300.
        counter.hit(300);
        // get hits at timestamp 300, should return 4.
        System.out.println(counter.getHits(300));
        // get hits at timestamp 301, should return 3.
        System.out.println(counter.getHits(301));
    }
}
