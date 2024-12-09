public class Main {
    public static int maxSubArrayPrefix(int[] nums) {
        int len = nums.length;
        int[] prefixSum = new int[len];
        int minPrefixSum = 0;
        int max = Integer.MIN_VALUE;

        prefixSum[0] = nums[0];
        if (prefixSum[0] > max) {
            max = prefixSum[0];
        }
        if (prefixSum[0] < minPrefixSum) {
            minPrefixSum = prefixSum[0];
        }
        for (int i = 1; i < len; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
            int curMax = prefixSum[i] - minPrefixSum;
            if (curMax > max) {
                max = curMax;
            }
            if (prefixSum[i] < minPrefixSum) {
                minPrefixSum = prefixSum[i];
            }
        }
        
        return max;
    }

    public static void main(String[] args) {
        int[] input = {-2,1,-3,4,-1,2,1,-5,4};
        int ret = maxSubArrayPrefix(input);
        System.out.println(ret);
    }
}

