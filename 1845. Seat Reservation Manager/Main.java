import java.util.*;


class SeatManager {
    boolean[] isAvailable;
    PriorityQueue<Integer> available;

    public SeatManager(int n) {
        isAvailable = new boolean[n + 1];
        available = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            available.add(i);
            isAvailable[i] = true;
        }
    }

    public int reserve() {
        int min = available.poll();
        isAvailable[min] = false;
        return min;
    }

    public void unreserve(int seatNumber) {
        available.add(seatNumber);
        isAvailable[seatNumber] = true;
    }
}
public class Main {
    public static void main(String[] args) {
        SeatManager seatManager = new SeatManager(5); // Initializes a SeatManager with 5 seats.
        System.out.println(seatManager.reserve());    // All seats are available, so return the lowest numbered seat, which is 1.
        System.out.println(seatManager.reserve());    // The available seats are [2,3,4,5], so return the lowest of them, which is 2.
        seatManager.unreserve(2); // Unreserve seat 2, so now the available seats are [2,3,4,5].
        System.out.println(seatManager.reserve());    // The available seats are [2,3,4,5], so return the lowest of them, which is 2.
        System.out.println(seatManager.reserve());    // The available seats are [3,4,5], so return the lowest of them, which is 3.
        System.out.println(seatManager.reserve());    // The available seats are [4,5], so return the lowest of them, which is 4.
        System.out.println(seatManager.reserve());    // The only available seat is seat 5, so return 5.
        seatManager.unreserve(5); // Unreserve seat 5, so now the available seats are [5].
    }
}
