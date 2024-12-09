import java.util.*;

class MyCalendar {
    private List<int[]> events;
    public MyCalendar() {
        events = new ArrayList<>();
    }
    public boolean isDoubleBooking(int start, int end, int[] old) {
        return start < old[1] && old[0] < end;
    }

    public boolean book(int start, int end) {
        if (events.isEmpty()) {
            events.add(new int[]{start, end});
            return true;
        }
        int len = events.size(), s = 0, e = len - 1, midIdx, pos;
        int[] mid;
        while (true) {
            midIdx = (s + e) / 2;
            mid = events.get(midIdx);
            if (isDoubleBooking(start, end, mid)) {
                return false;
            }
            if (end <= mid[0]) {
                e = midIdx - 1;
                if (s > e) {
                    pos = s;
                    break;
                }
            } else {
                s = midIdx + 1;
                if (s > e) {
                    pos = e + 1;
                    break;
                }
            }
        }
        if (pos - 1 >= 0 && events.get(pos - 1)[1] == start &&
            pos < len && events.get(pos)[0] == end) {
            events.get(pos)[0] = events.get(pos - 1)[0];
            events.remove(pos - 1);
        } else if (pos - 1 >= 0 && events.get(pos - 1)[1] == start) {
            events.get(pos - 1)[1] = end;
        } else if (pos < len && events.get(pos)[0] == end) {
            events.get(pos)[0] = start;
        } else {
            events.add(pos, new int[]{start, end});
        }
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
