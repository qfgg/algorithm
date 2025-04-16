import java.util.*;

class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        HashMap<Integer, Integer> count = new HashMap<>();
        List<Integer> arr = new ArrayList<>();
        for (int num : nums) {
            if (count.containsKey(num)) {
                count.put(num, count.get(num) + 1);
            } else {
                arr.add(num);
                count.put(num, 1);
            }
        }
        Collections.sort(arr);
        int len = arr.size(), r = arr.get(0), m = r - k, l, lIdx = 0, rIdx = 0, mIdx = -1;
        int toMove = count.get(arr.get(0)), base = 0, max = Math.min(numOperations, toMove);
        while (mIdx < len - 1) {
            if (rIdx >= len - 1 || arr.get(mIdx + 1) - m <= arr.get(rIdx + 1) - r)  {
                if (rIdx < len - 1 && arr.get(mIdx + 1) - m == arr.get(rIdx + 1) - r) {
                    rIdx++;
                    toMove += count.get(arr.get(rIdx));
                }
                mIdx++;
                m = arr.get(mIdx);
                toMove += base;
                toMove -= count.get(arr.get(mIdx));
                base = count.get(arr.get(mIdx));
                r = m + k;
                l = m - k;
            } else {
                rIdx++;
                r = arr.get(rIdx);
                if (count.containsKey(m)) {
                    toMove += count.get(m);
                }
                m = r - k;
                l = m - k;
                toMove += count.get(arr.get(rIdx));
                base = 0;
            }
            while (arr.get(lIdx) < l) {
                toMove -= count.get(arr.get(lIdx));
                lIdx++;
            }
            max = Math.max(max, base + Math.min(numOperations, toMove));
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{36,36,64,118};
        int res = s.maxFrequency(nums, 58, 1);
        System.out.println(res);
    }
}
