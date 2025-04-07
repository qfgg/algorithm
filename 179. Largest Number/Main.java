import java.util.*;

class Solution {
    private int customCompare(Integer a, Integer b) {
        int af = a, bf = b, an = 0, bn = 0;
        while (af >= 10) {
            af = af / 10;
            an++;
        }
        while (bf >= 10) {
            bf = bf / 10;
            bn++;
        }
        if (bf != af) {
            return bf - af;
        }
        an++;
        bn++;
        double d = Math.pow(10, Math.max(an, bn));
        double res = b * Math.pow(10, an) / d - a * Math.pow(10, bn) / d + (a - b) / d;
        if (res == 0) {
            return 0;
        }
        return res > 0 ? 1 : -1;
    }
    public String largestNumber(int[] nums) {
        Integer[] integerNums = Arrays.stream(nums)
                .boxed()
                .toArray(Integer[]::new);
        Arrays.sort(integerNums, (a, b) -> customCompare(a, b));
        StringBuilder result = new StringBuilder();
        for (Integer num : integerNums) {
            result.append(num);
        }
        String res = result.toString();
        if (res.charAt(0) == '0' && res.length() > 1) {
            return "0";
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = { 3,30,34,5,9 };
        String res = s.largestNumber(nums);
        System.out.println(res);
    }
}
