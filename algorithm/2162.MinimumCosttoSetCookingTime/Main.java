import java.util.*;

public class Main {
    public static int getCost(int minutes, int seconds, int startAt, int moveCost, int pushCost) {
        int[] d = new int[]{minutes / 10, minutes % 10, seconds / 10, seconds % 10};
        int cost = 0, i = 0, pre = startAt;
        while (d[i] == 0) {
            i++;
        }
        while (i < 4) {
            if (d[i] != pre) {
                cost += moveCost;
            }
            cost += pushCost;
            pre = d[i];
            i++;
        }
        return cost;
    }
    public static int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        int minutes = targetSeconds / 60;
        int seconds = targetSeconds % 60;
        if (minutes == 100) {
            minutes = 99;
            seconds += 60;
        }
        int res = getCost(minutes, seconds, startAt, moveCost, pushCost);
        if (seconds <= 39) {
            res = Math.min(res, getCost(minutes - 1, seconds + 60, startAt, moveCost, pushCost));
        }
        return res;
    }
    public static void main(String[] args) {
        int startAt = 9, moveCost = 100000, pushCost = 1, targetSeconds = 6039;
        System.out.println(minCostSetTime(startAt, moveCost, pushCost, targetSeconds));
    }
}
