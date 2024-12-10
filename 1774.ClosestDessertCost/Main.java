import java.util.*;


public class Main {
    public static boolean isCloser(int target, int oldCost, int newCost) {
        if (oldCost > target && newCost > target) {
            return newCost < oldCost;
        }
        if (oldCost < target && newCost < target) {
            return newCost > oldCost;
        }
        if (oldCost > target) {
            return (target - newCost) <= (oldCost - target);
        }
        return (newCost - target) < (target - oldCost);
    }
    public static int tryABase(int baseCost, int[] toppingCosts, int target, int newTarget) {
        if (baseCost == target) {
            return target;
        }
        if (baseCost > target) {
            if (newTarget < target) {
                return target - newTarget <= baseCost - target ? newTarget : baseCost;
            }
            return Math.min(baseCost, newTarget);
        }
        int rest = newTarget > target ? newTarget - baseCost : target * 2 - newTarget - 1 - baseCost;
        int[] dp = new int[rest + 1];
        int tl = toppingCosts.length, candidate = newTarget;
        for (int i = 0; i < tl; i++) {
            for (int j = rest; j >= 0; j--) {
                for (int k = 1; k < 3; k++) {
                    if (j >= k * toppingCosts[i] && dp[j - k * toppingCosts[i]] + k * toppingCosts[i] > dp[j]) {
                        dp[j] = dp[j - k * toppingCosts[i]] + k * toppingCosts[i];
                        if (dp[j] + baseCost == target) {
                            return target;
                        }
                    }
                    if (isCloser(target, candidate, dp[j] + baseCost)) {
                        candidate = dp[j] + baseCost;
                    }
                }
            }
        }
        return candidate;
    }
    public static int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        int rest = target, newTarget, min = Integer.MAX_VALUE, bl = baseCosts.length, i, j;
        for (i = 0; i < bl; i++) {
            if (baseCosts[i] == target) {
                return target;
            }
            if (baseCosts[i] < target) {
                rest = target - baseCosts[i];
                break;
            }
            if (baseCosts[i] < min) {
                min = baseCosts[i];
            }
        }
        if (i == bl) {
            return min;
        }
        for (int tc : toppingCosts) {
            if (rest - tc == 0 || rest - 2 * tc == 0) {
                return target;
            }
            if (rest - tc < 0) {
                break;
            }
            if (rest - 2 * tc < 0) {
                rest -= tc;
                break;
            }
            rest -= 2 * tc;
        }
        newTarget = target - rest;
        for (i = 0; i < bl; i++) {
            newTarget = tryABase(baseCosts[i], toppingCosts, target, newTarget);
            if (newTarget == target) {
                return target;
            }
        }
        return newTarget;
    }
    public static void main(String[] args) {
        int[] baseCosts = new int[]{8,4,4,5,8}, toppingCosts = new int[]{3,10,9,10,8,10,10,9,3};
        int target = 6;
        int ans = closestCost(baseCosts, toppingCosts, target);
        System.out.println(ans);
    }
}
