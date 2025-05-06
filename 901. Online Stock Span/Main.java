import java.util.*;


class StockSpanner {
    Deque<int[]> s;
    public StockSpanner() {
        s = new ArrayDeque<>();
    }

    public int next(int price) {
        int count = 1;
        int[] top;
        while (!s.isEmpty() && price >= s.peek()[0]) {
            top = s.pop();
            count += top[1];
        }
        s.push(new int[]{price, count});
        return count;
    }
}
public class Main {
    public static void main(String[] args) {
        StockSpanner stockSpanner = new StockSpanner();
        System.out.println(stockSpanner.next(100)); // return 1
        System.out.println(stockSpanner.next(80));  // return 1
        System.out.println(stockSpanner.next(60));  // return 1
        System.out.println(stockSpanner.next(70));  // return 2
        System.out.println(stockSpanner.next(60));  // return 1
        System.out.println(stockSpanner.next(75));  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
        System.out.println(stockSpanner.next(85));  // return 6
    }
}
