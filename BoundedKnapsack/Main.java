import java.util.*;

public class Main {
//    straightforward but slow
    public static int boundedKnapsack0(int W, int N, int[] w, int[] v, int[] n) {
        int[] dp = new int[W + 1];
        for (int i = 0; i < N; i++) {
            for (int j = W; j >= 0; j--) {
                for (int k = 0; k <= n[i] && k * w[i] <= j; k++) {
                    if (dp[j - k * w[i]] + k * v[i] > dp[j]) {
                        dp[j] = dp[j - k * w[i]] + k * v[i];
                    }
                }
            }
        }
        return dp[W];
    }

//    monotonic queue
    public static int boundedKnapsack(int W, int N, int[] w, int[] v, int[] n) {
        int[] dp = new int[W + 1];
        int i, j, k, p, last, first;
        Deque<Integer> mq = new ArrayDeque<>();
        for (i = 0; i < N; i++) {
            for (k = 0; k < w[i]; k++) {
                mq.clear();
                p = 0;
                while (p <= n[i] && W - k - p * w[i] >= 0) {
                    if (mq.isEmpty()) {
                        mq.addLast(W - k);
                    } else {
                        last = mq.peekLast();
                        while (dp[W - k - p * w[i]] + p * v[i] >= dp[last] + (W - k - last) / w[i] * v[i]) {
                            mq.removeLast();
                            if (mq.isEmpty()) {
                                break;
                            }
                            last = mq.peekLast();
                        }
                        mq.addLast(W - k - p * w[i]);
                    }
                    p++;
                }
                if (!mq.isEmpty()) {
                    first = mq.peekFirst();
                    dp[W - k] = dp[first] + (W - k - first) / w[i] * v[i];
                }
                j = 1;
                while (W - k - j * w[i] >= 0) {
                    if (!mq.isEmpty() && mq.peekFirst() > W - k - j * w[i]) {
                        mq.removeFirst();
                    }
                    if (W - k - p * w[i] >= 0) {
                        if (mq.isEmpty()) {
                            mq.addLast(W - k - p * w[i]);
                        } else {
                            last = mq.peekLast();
                            while (dp[W - k - p * w[i]] + p * v[i] >= dp[last] + (W - k - last) / w[i] * v[i]) {
                                mq.removeLast();
                                if (mq.isEmpty()) {
                                    break;
                                }
                                last = mq.peekLast();
                            }
                            mq.addLast(W - k - p * w[i]);
                        }
                    }
                    if (!mq.isEmpty()) {
                        first = mq.peekFirst();
                        dp[W - k - j * w[i]] =
                                dp[first] + (W - k - j * w[i] - first) / w[i] * v[i];
                    }
                    p++;
                    j++;
                }
            }
        }
        return dp[W];
    }

    public static void main(String[] args) {
        int W = 7;
        int N = 4;
        int[] w = new int[4];
        int[] v = new int[4];
        int[] n = new int[4];
        Random r = new Random();
        int res0, res, j;
        int i = 2000;
        while (i > 0) {
            for (j = 0; j < 4; j++) {
                w[j] = r.nextInt(10) + 1;
                v[j] = r.nextInt(10) + 1;
                n[j] = r.nextInt(10) + 1;
            }
            res0 = boundedKnapsack0(W, N, w, v, n);
            res = boundedKnapsack(W, N, w, v, n);
            if (res0 != res) {
                System.out.println(Arrays.toString(w));
                System.out.println(Arrays.toString(v));
                System.out.println(Arrays.toString(n));
                System.out.println(res0);
                System.out.println(res);
            }
            i--;
        }
    }
}
