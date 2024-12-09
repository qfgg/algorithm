public class Main {
    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        int max = Integer.MIN_VALUE;

        dp[0] = nums[0];
        if (dp[0] > max) {
            max = dp[0];
        }
        for (int i = 1; i < len; i++) {
            int subEndsWithI = dp[i - 1] + nums[i];
            dp[i] = nums[i] > subEndsWithI ? nums[i] : subEndsWithI;
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        
        return max;
    }

    public static void main(String[] args) {
        int[] input = {-2,1,-3,4,-1,2,1,-5,4};
        int ret = maxSubArray(input);
        System.out.println(ret);
    }
}

