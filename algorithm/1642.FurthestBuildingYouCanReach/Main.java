import java.util.*;

class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int n = heights.length, i, upCnt = 0, upSum = 0, topSum = 0;
        PriorityQueue<Integer> upPQ = new PriorityQueue<>();
        for (i = 1; i < n; i++) {
            if (heights[i] > heights[i - 1]) {
                upCnt++;
                upSum += heights[i] - heights[i - 1];
                topSum += heights[i] - heights[i - 1];
                upPQ.add(heights[i] - heights[i - 1]);
                if (upCnt > ladders) {
                    topSum -= upPQ.poll();
                }
                if (upSum - topSum > bricks) {
                    break;
                }
            }
        }
        return i - 1;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] heights = new int[]{4,12,2,7,3,18,20,3,19};
        int bricks = 10, ladders = 2;
        int res = s.furthestBuilding(heights, bricks, ladders);
        System.out.println(res);
    }
}
