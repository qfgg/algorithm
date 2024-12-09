import java.util.*;


public class Main {
    public static int[] productExceptSelf(int[] nums) {
        int l = nums.length, i, suffixProduct;
        int[] ans = new int[l];
        ans[0] = nums[0];
        for (i = 1; i < l - 1; i++) {
            ans[i] = ans[i - 1] * nums[i];
        }
        ans[l - 1] = ans[l - 2];
        suffixProduct = nums[l - 1];
        for (i = l - 2; i >= 1; i--) {
            ans[i] = ans[i - 1] * suffixProduct;
            suffixProduct *= nums[i];
        }
        ans[0] = suffixProduct;
        return ans;
    }
    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,4};
        int[] ans = productExceptSelf(nums);
        System.out.println(Arrays.toString(ans));
    }
}
