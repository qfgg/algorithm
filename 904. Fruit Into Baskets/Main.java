import java.util.*;


class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        if (n == 1) {
            return 1;
        }
        int left = 0, right = 0, max = 0, curTotal = 0, curTypes = 0;
        int[] typeCount = new int[n];
        while (left <= right && right < n) {
            if (typeCount[fruits[right]] == 0) {
                curTypes++;
            }
            typeCount[fruits[right]]++;
            while (curTypes > 2 && left <= right) {
                typeCount[fruits[left]]--;
                curTotal--;
                if (typeCount[fruits[left]] == 0) {
                    curTypes--;
                }
                left++;
            }
            right++;
            curTotal++;
            max = Math.max(max, curTotal);
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] fruits = new int[]{3,3,3,1,2,1,1,2,3,3,4};
        int res = s.totalFruit(fruits);
        System.out.println(res);
    }
}
