import java.util.*;


public class Main {
    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> lens = new HashMap<>();
        int max = 1, ll, lr, len = 0;
        boolean hasLeft, hasRight;
        for (int num: nums) {
            if (lens.containsKey(num)) {
                continue;
            }
            hasLeft = lens.containsKey(num - 1);
            hasRight = lens.containsKey(num + 1);
            if (!hasLeft && !hasRight) {
                lens.put(num, 1);
            } else if (hasLeft && hasRight) {
                ll = lens.get(num - 1);
                lr = lens.get(num + 1);
                len = ll + lr + 1;
                lens.put(num, len);
                lens.put(num - ll, len);
                lens.put(num + lr, len);
            } else if (hasLeft) {
                ll = lens.get(num - 1);
                len = ll + 1;
                lens.put(num, len);
                lens.put(num - ll, len);
            } else {
                lr = lens.get(num + 1);
                len = lr + 1;
                lens.put(num, len);
                lens.put(num + lr, len);
            }
            if (len > max) {
                max= len;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{-6,6,-9,-7,0,3,4,-2,2,-1,9,-9,5,-3,6,1,5,-1,-2,9,-9,-4,-6,-5,6,-1,3};
        int ans = longestConsecutive(nums);
        System.out.println(ans);
    }
}
