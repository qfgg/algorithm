import java.sql.Time;
import java.util.*;


class TimeList {
    List<Integer> timestamps;
    HashMap<Integer, String> timeValMap;
    TimeList(List<Integer> tl, HashMap<Integer, String> tvmap) {
        timestamps = tl;
        timeValMap = tvmap;
    }
}
class TimeMap {
    HashMap<String, TimeList> keymap;
    public TimeMap() {
        keymap = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        TimeList tl;
        if (keymap.containsKey(key)) {
            tl = keymap.get(key);
            tl.timestamps.add(timestamp);
            tl.timeValMap.put(timestamp, value);
        } else {
            HashMap<Integer, String> tvmap = new HashMap<>();
            tvmap.put(timestamp, value);
            tl = new TimeList(new ArrayList<>(List.of(timestamp)), tvmap);
            keymap.put(key, tl);
        }
    }

    public String get(String key, int timestamp) {
        if (!keymap.containsKey(key)) {
            return "";
        }
        TimeList tl = keymap.get(key);
        List<Integer> timestamps = tl.timestamps;
        HashMap<Integer, String> timeValMap = tl.timeValMap;
        int left = 0, right = timestamps.size(), mid, mt, maxleft = -1;
        while (left < right) {
            mid = (left + right) / 2;
            mt = timestamps.get(mid);
            if (mt == timestamp) {
                return timeValMap.get(mt);
            }
            if (timestamp > mt) {
                maxleft = Math.max(maxleft, mt);
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return maxleft == -1 ? "" : timeValMap.get(maxleft);
    }
}
public class Main {
    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("love", "high", 10);
        timeMap.set("love", "low", 20);
        System.out.println(timeMap.get("love", 5));
        System.out.println(timeMap.get("love", 10));
        System.out.println(timeMap.get("love", 15));
        System.out.println(timeMap.get("love", 20));
        System.out.println(timeMap.get("love", 25));
    }
}
