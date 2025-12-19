import java.util.*;


class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int total = hand.length;
        if (total % groupSize != 0) {
            return false;
        }
        Arrays.sort(hand);
        Map<Integer, Integer> remain = new HashMap<>();
        List<Integer> uniques = new ArrayList<>();
        int i, curCount, doneCount = 0, last = -1;
        for (i = 0; i < total; i++) {
            curCount = remain.getOrDefault(hand[i], 0);
            remain.put(hand[i], curCount + 1);
            if (hand[i] != last) {
                last = hand[i];
                uniques.add(last);
            }
        }
        int n = uniques.size();
        int cur = 0, end, val;
        while (cur < n) {
            val = uniques.get(cur);
            end = val + groupSize;
            for (i = val; i < end; i++) {
                if (!remain.containsKey(i)) {
                    return false;
                }
                curCount = remain.get(i);
                if (curCount == 1) {
                    cur++;
                }
                remain.put(i, curCount - 1);
                doneCount++;
            }
        }
        return doneCount == total;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] hand = new int[]{34,80,89,15,38,69,19,17,97,98,26,77,8,31,79,70,103,3,13,21,81,53,33,14,60,68,33,59,84,23,97,90,76,82,66,83,23,22,16,18,98,25,16,61,84,100,4,68,101,25,23,9,10,55,2,67,39,52,102,99,40,11,83,24,81,53,96,23,13,24,99,67,22,51,31,58,78,88,5,15,24,32,81,91,96,16,54,22,56,69,14,82,32,34,83,24,37,82,54,21};
        int groupSize = 4;
        boolean res = s.isNStraightHand(hand, groupSize);
        System.out.println(res);
    }
}
