import java.util.*;


class Solution {
    boolean dfs(String str, int n, int start, List<Long> numbers) {
        boolean res;
        long cur, pre1, pre2;
        for (int i = 1; i <= 17 && start + i <= n; i++) {
            if (str.charAt(start) == '0' && i > 1) {
                return false;
            }
            cur = Long.parseLong(str.substring(start, start + i));
            if (numbers.size() > 1) {
                pre1 = numbers.getLast();
                pre2 = numbers.get(numbers.size() - 2);
                if (cur != pre1 + pre2) {
                    continue;
                }
                if (start + i == n) {
                    return true;
                }
            }
            numbers.add(cur);
            res = dfs(str, n, start + i, numbers);
            numbers.removeLast();
            if (res) {
                return true;
            }
        }
        return false;
    }
    public boolean isAdditiveNumber(String num) {//1 <= num.length <= 35, so max num length is 17
        List<Long> numbers = new ArrayList<>();
        return dfs(num, num.length(), 0, numbers);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String num = "101";
        boolean res = s.isAdditiveNumber(num);
        System.out.println(res);
    }
}
