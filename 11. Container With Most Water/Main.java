import java.util.*;

class Solution {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1, next, max = Math.min(height[l], height[r]) * (r);
        while (l < r) {
            if (height[l] <= height[r]) {
                next = l + 1;
                while (next < r && height[next] <= height[l]) {
                    next++;
                }
                if (next == r) {
                    break;
                }
                max = Math.max(max, Math.min(height[next], height[r]) * (r - next));
                l = next;
            } else {
                next = r - 1;
                while (next > l && height[next] <= height[r]) {
                    next--;
                }
                if (next == l) {
                    break;
                }
                max = Math.max(max, Math.min(height[next], height[l]) * (next - l));
                r = next;
            }
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] height = { 1,8,6,2,5,4,8,3,7 };
        int res = s.maxArea(height);
        System.out.println(res);
    }
}
