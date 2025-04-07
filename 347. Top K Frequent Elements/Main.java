import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        List<Integer> l = new ArrayList<>();
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (count.containsKey(nums[i])) {
                count.put(nums[i], count.get(nums[i]) + 1);
            } else {
                count.put(nums[i], 1);
                l.add(nums[i]);
            }
        }
        Collections.sort(l, (a, b) -> count.get(b) -count.get(a));
        int kn = Math.min(k, l.size());
        int[] top = new int[kn];
        for (int i = 0; i < kn; i++) {
            top[i] = l.get(i);
        }
        return top;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = { 1,1,1,2,2,3 };
        int k = 2;
        int[] res = s.topKFrequent(nums, k);
        System.out.println(Arrays.toString(res));
    }
}
