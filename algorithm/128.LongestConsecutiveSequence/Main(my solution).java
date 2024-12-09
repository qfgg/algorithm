import java.util.*;


public class Main {
    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> cons = new HashMap<>();
        int max = 1, leftEnd, rightEnd, len;
        boolean hasLeft, hasRight;
        for (int num: nums) {
            if (cons.containsKey(num)) {
                continue;
            }
            hasLeft = cons.containsKey(num - 1);
            hasRight = cons.containsKey(num + 1);
            if (!hasLeft && !hasRight) {
                if (!cons.containsKey(num)) {
                    cons.put(num, num);
                }
            } else {
                len = 0;
                if (hasLeft && !hasRight) {
                    leftEnd = cons.get(num - 1);
                    if (leftEnd < num) {
                        cons.remove(num - 1);
                        cons.put(leftEnd, num);
                        cons.put(num, leftEnd);
                        len = num - leftEnd + 1;
                    }
                } else if (!hasLeft) {
                    rightEnd = cons.get(num + 1);
                    if (rightEnd > num) {
                        cons.remove(num + 1);
                        cons.put(rightEnd, num);
                        cons.put(num, rightEnd);
                        len = rightEnd - num + 1;
                    }
                } else {
                    leftEnd = cons.get(num - 1);
                    rightEnd = cons.get(num + 1);
                    if (leftEnd < num && rightEnd > num) {
                        cons.remove(num - 1);
                        cons.remove(num + 1);
                        cons.put(leftEnd, rightEnd);
                        cons.put(rightEnd, leftEnd);
                        len = rightEnd - leftEnd + 1;
                    }
                }
                if (len > max) {
                    max = len;
                }
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
