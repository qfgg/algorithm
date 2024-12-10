import java.util.*;

class StockPrice {
    private PriorityQueue<int[]> maxHeap;
    private PriorityQueue<int[]> minHeap;
    private Map<Integer, Integer> timePrice;
    private int current;

    public StockPrice() {
        maxHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        timePrice = new HashMap<>();
        current = 0;
    }

    public void update(int timestamp, int price) {
        int[] record = new int[]{timestamp, price};
        maxHeap.add(record);
        minHeap.add(record);
        timePrice.put(timestamp, price);
        if (timestamp > current) {
            current = timestamp;
        }
    }

    public int current() {
        return timePrice.get(current);
    }

    public int maximum() {
        int[] max = maxHeap.peek();
        while (timePrice.get(max[0]) != max[1]) {
            maxHeap.poll();
            max = maxHeap.peek();
        }
        return max[1];
    }

    public int minimum() {
        int[] min = minHeap.peek();
        while (timePrice.get(min[0]) != min[1]) {
            minHeap.poll();
            min = minHeap.peek();
        }
        return min[1];
    }
}
public class Main {
    public static void main(String[] args) {
        StockPrice obj = new StockPrice();
        obj.update(1, 10);
        obj.update(2, 5);
        System.out.println(obj.current());
        System.out.println(obj.maximum());
        obj.update(1, 3);
        System.out.println(obj.maximum());
        obj.update(4, 2);
        System.out.println(obj.minimum());
    }
}
