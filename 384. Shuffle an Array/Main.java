import java.util.*;


class Solution {
    private int[] origin;
    Random rd;

    public Solution(int[] nums) {
        origin = nums;
        rd = new Random();
    }

    public int[] reset() {
        return Arrays.copyOf(origin, origin.length);
    }

    void randomSwapOne(int[] nums, int n, int startIdx) {
        int target = rd.nextInt(n - startIdx) + startIdx;
        if (target == startIdx) {
            return;
        }
        int tmp = nums[startIdx];
        nums[startIdx] = nums[target];
        nums[target] = tmp;
    }

    public int[] shuffle() {
        int i, n = origin.length;
        int[] copy = Arrays.copyOf(origin, n);
        for (i = 0; i < n - 1; i++) {
            randomSwapOne(copy, n, i);
        }
        return copy;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution(new int[]{1, 2, 3});
        int[] res1 = solution.shuffle();    // Shuffle the array [1,2,3] and return its result.
        System.out.println(Arrays.toString(res1));
        // Any permutation of [1,2,3] must be equally likely to be returned.
        // Example: return [3, 1, 2]
        int[] res2 = solution.reset();      // Resets the array back to its original configuration [1,2,3]. Return [1, 2, 3]
        System.out.println(Arrays.toString(res2));
        int[] res3 = solution.shuffle();    // Returns the random shuffling of array [1,2,3]. Example: return [1, 3, 2]
        System.out.println(Arrays.toString(res3));
    }
}
