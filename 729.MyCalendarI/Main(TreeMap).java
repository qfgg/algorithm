import java.util.*;

class MyCalendar {
    TreeMap<Integer, Integer> timeline;
    public MyCalendar() {
        timeline = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Integer l = timeline.floorKey(start);
        Integer r = timeline.higherKey(start);
        if (l != null && (l.equals(start) || timeline.get(l) > start)) {
            return false;
        }
        if (r != null && end > r) {
            return false;
        }
        timeline.put(start, end);
        return true;
    }
}
public class Main {
    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        System.out.println(myCalendar.book(10, 20)); // return True
        System.out.println(myCalendar.book(15, 25)); // return False, It can not be booked because time 15 is already booked by another event.
        System.out.println(myCalendar.book(24, 30)); // return True
        System.out.println(myCalendar.book(40, 45));
        System.out.println(myCalendar.book(20, 24));
    }
}
