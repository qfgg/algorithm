import java.util.*;


class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int low = 0, high = people.length - 1, cnt  = 0;
        while (low < high) {
            if (people[low] + people[high] <= limit) {
                cnt++;
                low++;
                high--;
            } else {
                cnt++;
                high--;
            }
        }
        if (low == high) {
            cnt++;
        }
        return cnt;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] people = new int[]{3,5,3,4};
        int limit = 5;
        int res = s.numRescueBoats(people, limit);
        System.out.println(res);
    }
}
