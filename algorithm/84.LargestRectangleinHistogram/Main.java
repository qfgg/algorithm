import java.util.*;


public class Main {
    public static int largestRectangleArea(int[] heights) {
        // each bar width 1. 0: height, 1: accumulated width from other bar
        Deque<int[]> ms = new ArrayDeque<>();
        int l = heights.length, max = 0, area;
        int[] top, preTop, next;
        for (int i = 0; i < l; i++) {
            next = new int[]{heights[i], 0};
            if (ms.isEmpty()) {
                ms.push(next);
            } else {
                preTop = ms.peek();
                while (heights[i] < preTop[0]) {
                    ms.pop();
                    area = preTop[0] * (preTop[1] + next[1] + 1);
                    if (area > max) {
                        max = area;
                    }
                    next[1] += preTop[1] + 1;
                    if (ms.isEmpty()) {
                        break;
                    }
                    preTop = ms.peek();
                }
                if (heights[i] == preTop[0]) {
                    preTop[1] += next[1] + 1;
                } else {
                    ms.push(next);
                }
            }
        }
        while (!ms.isEmpty()) {
            preTop = ms.pop();
            area = preTop[0] * (preTop[1] + 1);
            if (area > max) {
                max = area;
            }
            top = ms.peek();
            if (top != null) {
                top[1] += preTop[1] + 1;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[] heights = new int[]{5,3,6,3,6,8,6,1,6,2,0};
        int res = largestRectangleArea(heights);
        System.out.println(res);
    }
}
