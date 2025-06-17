import java.util.*;


class MyCalendarTwo {
    TreeMap<Integer, Integer> single;
    TreeMap<Integer, Integer> dbOverlap;

    public MyCalendarTwo() {
        single = new TreeMap<>();
        dbOverlap = new TreeMap<>();
    }

    void splitSingle(int startTime, int endTime, int r) {
        List<int[]> conflicts = new ArrayList<>();
        Integer cur = r;
        while (cur != null && cur < endTime && single.get(cur) > startTime) {
            conflicts.add(new int[]{cur, single.get(cur)});
            cur = single.lowerKey(cur);
        }
        int n = conflicts.size(), i, lmin = 0, rmax, dbl = 0, dbr, end = endTime;
        int[] span;
        for (i = 0; i < n; i++) {
            span = conflicts.get(i);
            single.remove(span[0]);
            if (startTime < span[0]) {
                lmin = startTime;
                dbl = span[0];
            } else {
                lmin = span[0];
                dbl = startTime;
            }
            if (end < span[1]) {
                rmax = span[1];
                dbr = end;
            } else {
                rmax = end;
                dbr = span[1];
            }
            if (dbr < rmax) {
                single.put(dbr, rmax);
            }
            dbOverlap.put(dbl, dbr);
            end = dbl;
        }
        if (lmin < dbl) {
            single.put(lmin, dbl);
        }
    }

    public boolean book(int startTime, int endTime) {
        Integer r = dbOverlap.lowerKey(endTime), l;
        if (r != null && dbOverlap.get(r) > startTime) {
            return false;
        }
        r = single.lowerKey(endTime);
        if (r != null && single.get(r) > startTime) {
            splitSingle(startTime, endTime, r);
        } else {
            single.put(startTime, endTime);
        }
        return true;
    }
}
public class Main {
    public static void main(String[] args) {
        MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
        System.out.println(myCalendarTwo.book(10, 20)); // True
        System.out.println(myCalendarTwo.book(30, 40)); // True
        System.out.println(myCalendarTwo.book(55, 60)); // True
        System.out.println(myCalendarTwo.book(60, 80));  // True
        System.out.println(myCalendarTwo.book(15, 75)); // True
        System.out.println();
    }
}
