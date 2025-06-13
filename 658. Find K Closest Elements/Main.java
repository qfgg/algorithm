import java.util.*;


class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int n = arr.length, l = 0, nl = k, i, curBest = 0;
        int abs1, abs2;
        while (nl < n) {
            abs1 = Math.abs(arr[nl] - x);
            abs2 = Math.abs(arr[l] - x);
            if(abs1 < abs2) {
                l++;
                nl++;
                curBest = l;
            } else if (abs1 == abs2) {
                l++;
                nl++;
            } else {
                break;
            }
        }
        nl = curBest + k;
        for (i = curBest; i < nl; i++) {
            res.add(arr[i]);
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = new int[]{1,1,1,10,10,10};
        int k = 1, x = 9;
        List<Integer> res = s.findClosestElements(arr, k, x);
        System.out.println(res);
    }
}
