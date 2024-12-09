import java.util.*;


public class Main {
    public static int minCost(String colors, int[] neededTime) {
        char pre = Character.MIN_VALUE, cur;
        int min = 0, curCost = 0, len = neededTime.length, i = 0, curMax = 0;
        while (i < len) {
            cur = colors.charAt(i);
            if (cur == pre) {
                curCost += neededTime[i];
                if (neededTime[i] > curMax) {
                    curMax = neededTime[i];
                }
            } else {
                if (curCost > curMax) {
                    min += curCost - curMax;
                }
                curCost = neededTime[i];
                curMax = curCost;
            }
            pre = cur;
            i++;
        }
        if (curCost > curMax) {
            min += curCost - curMax;
        }
        return min;
    }
    public static void main(String[] args) {
        String colors = "aabaa";
        int[] neededTime = new int[]{1,2,3,4,1};
        int ans = minCost(colors, neededTime);
        System.out.println(ans);
    }
}
