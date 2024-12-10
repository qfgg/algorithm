import java.util.*;

public class Main {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length, w = n - k + 1, i, tail;
        int[] res = new int[w];
        Deque<Integer> mq = new ArrayDeque<>();
        for (i = 0; i < n; i++) {
            if (i >= k) {
                if (mq.peekFirst() == nums[i - k]) {
                    mq.removeFirst();
                }
            }
            if (mq.isEmpty()) {
                mq.add(nums[i]);
            } else if (nums[i] > mq.peekFirst()) {
                mq.clear();
                mq.add(nums[i]);
            } else {
                tail = mq.peekLast();
                while (nums[i] > tail) {
                    mq.removeLast();
                    tail = mq.peekLast();
                }
                mq.add(nums[i]);
            }
            if (i >= k - 1) {
                res[i - k + 1] = mq.peekFirst();
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] res = maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(res));
    }
}
