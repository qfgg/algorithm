import java.util.*;


class StockPrice {
    private Map<Integer, Integer> time2price;
    private PriorityQueue<int[]> minq, maxq;
    private int lastUpdateAt;
    private int current;

    public StockPrice() {
        time2price = new HashMap<>();
        minq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        maxq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        lastUpdateAt = 0;
    }

    public void update(int timestamp, int price) {
        if (timestamp >= lastUpdateAt) {
            lastUpdateAt = timestamp;
            current = price;
        }
        time2price.put(timestamp, price);
        minq.add(new int[]{timestamp, price});
        maxq.add(new int[]{timestamp, price});
    }

    public int current() {
        return current;
    }

    public int maximum() {
        int[] max = maxq.peek();
        int realPrice = time2price.get(max[0]);
        while (realPrice != max[1]) {
            maxq.poll();
            max = maxq.peek();
            realPrice = time2price.get(max[0]);
        }
        return max[1];
    }

    public int minimum() {
        int[] min = minq.peek();
        int realPrice = time2price.get(min[0]);
        while (realPrice != min[1]) {
            minq.poll();
            min = minq.peek();
            realPrice = time2price.get(min[0]);
        }
        return min[1];
    }
}

public class Main {
    public static void main(String[] args) {
        StockPrice stockPrice = new StockPrice();
        stockPrice.update(1, 10); // Timestamps are [1] with corresponding prices [10].
        stockPrice.update(2, 5);  // Timestamps are [1,2] with corresponding prices [10,5].
        System.out.println(stockPrice.current());     // return 5, the latest timestamp is 2 with the price being 5.
        System.out.println(stockPrice.maximum());     // return 10, the maximum price is 10 at timestamp 1.
        stockPrice.update(1, 3);  // The previous timestamp 1 had the wrong price, so it is updated to 3.
        // Timestamps are [1,2] with corresponding prices [3,5].
        System.out.println(stockPrice.maximum());     // return 5, the maximum price is 5 after the correction.
        stockPrice.update(4, 2);  // Timestamps are [1,2,4] with corresponding prices [3,5,2].
        System.out.println(stockPrice.minimum());     // return 2, the minimum price is 2 at timestamp 4.
    }
}
