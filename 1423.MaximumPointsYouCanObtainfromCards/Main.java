import java.util.*;


public class Main {
    public static int maxScore(int[] cardPoints, int k) {
        int[] presum = new int[k];
        presum[0] = cardPoints[0];
        int n = cardPoints.length, i;
        for (i = 1; i < k; i++) {
            presum[i] = presum[i - 1] + cardPoints[i];
        }
        int max = presum[k - 1], suffix = 0, next;
        for (i = k - 2; i >= -1; i--) {
            suffix += cardPoints[n - k + i + 1];
            next = i == -1 ? suffix : presum[i] + suffix;
            if (next > max) {
                max = next;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[] cardPoints = new int[]{1,2,3,4,5,6,1};
        int k = 3;
        int res = maxScore(cardPoints, k);
        System.out.println(res);
    }
}
