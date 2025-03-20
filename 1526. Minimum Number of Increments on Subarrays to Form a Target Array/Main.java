import java.util.*;

class Solution {
    public int minNumberOperations(int[] target) {
        int len = target.length, num = 0;
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                num += target[i];
            } else if (target[i] > target[i - 1]) {
                num += target[i] - target[i - 1];
            }
        }
        return num;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] target = new int[]{3,1,5,4,2};
        int ret = s.minNumberOperations(target);
        System.out.println(ret);
    }
}
