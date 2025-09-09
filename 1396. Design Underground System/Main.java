import java.util.*;


class CheckIn {
    String stationName;
    int time;
    public CheckIn(String stationNm, int t) {
        stationName = stationNm;
        time = t;
    }
}
class UndergroundSystem {
    Map<Integer, CheckIn> inMap;
    Map<String, List<Integer>> pairTimes;

    public UndergroundSystem() {
        inMap = new HashMap<>();
        pairTimes = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        CheckIn ckIn = new CheckIn(stationName, t);
        inMap.put(id, ckIn);
    }

    public void checkOut(int id, String stationName, int t) {
        CheckIn ckIn = inMap.get(id);
        inMap.remove(id);
        String key = ckIn.stationName + "|" + stationName;
        List<Integer> times;
        if (pairTimes.containsKey(key)) {
            times= pairTimes.get(key);
        } else {
            times = new ArrayList<>();
            pairTimes.put(key, times);
        }
        times.add(t - ckIn.time);
    }

    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "|" + endStation;
        List<Integer> times = pairTimes.get(key);
        double sum = 0, n = times.size();
        for (Integer t : times) {
            sum += t;
        }
        return sum / n;
    }
}
public class Main {
    public static void main(String[] args) {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);  // Customer 45 "Leyton" -> "Waterloo" in 15-3 = 12
        undergroundSystem.checkOut(27, "Waterloo", 20);  // Customer 27 "Leyton" -> "Waterloo" in 20-10 = 10
        undergroundSystem.checkOut(32, "Cambridge", 22); // Customer 32 "Paradise" -> "Cambridge" in 22-8 = 14
        System.out.println(undergroundSystem.getAverageTime("Paradise", "Cambridge")); // return 14.00000. One trip "Paradise" -> "Cambridge", (14) / 1 = 14
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));    // return 11.00000. Two trips "Leyton" -> "Waterloo", (10 + 12) / 2 = 11
        undergroundSystem.checkIn(10, "Leyton", 24);
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));    // return 11.00000
        undergroundSystem.checkOut(10, "Waterloo", 38);  // Customer 10 "Leyton" -> "Waterloo" in 38-24 = 14
        System.out.println(undergroundSystem.getAverageTime("Leyton", "Waterloo"));    // return 12.00000. Three trips "Leyton" -> "Waterloo", (10 + 12 + 14) / 3 = 12
    }
}
