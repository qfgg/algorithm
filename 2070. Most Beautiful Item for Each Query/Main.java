import java.util.*;


class Solution {
    private int findFirstLowerPrice(int query, List<Integer> pl) {
        int l = 0, r = pl.size(), m, curPrice, firstLower = 0;
        while (l < r) {
            m = (l + r) / 2;
            curPrice = pl.get(m);
            if (curPrice == query) {
                return curPrice;
            }
            if (curPrice < query) {
                firstLower = Math.max(firstLower, curPrice);
                l = m + 1;
            } else {
                r = m;
            }
        }
        return firstLower;
    }
    public int[] maximumBeauty(int[][] items, int[] queries) {
        int i, n = items.length, lastMax;
        HashMap<Integer, Integer> bestItems = new HashMap<>();
        HashSet<Integer> prices = new HashSet<>();
        for (i = 0; i < n; i++) {
            if (bestItems.containsKey(items[i][0])) {
                lastMax = bestItems.get(items[i][0]);
                if (items[i][1] > lastMax) {
                    bestItems.put(items[i][0], items[i][1]);
                }
            } else {
                bestItems.put(items[i][0], items[i][1]);
            }
            prices.add(items[i][0]);
        }
        List<Integer> pl = new ArrayList<>(prices);
        Collections.sort(pl);
        int pn = pl.size();
        HashMap<Integer, Integer> bestBefore = new HashMap<>();
        bestBefore.put(pl.getFirst(), bestItems.get(pl.getFirst()));
        for (i = 1; i < pn; i++) {
            bestBefore.put(pl.get(i), Math.max(bestItems.get(pl.get(i)), bestBefore.get(pl.get(i - 1))));
        }
        int[] res = new int[queries.length];
        i = 0;
        int firstLower;
        for (int q : queries) {
            firstLower = findFirstLowerPrice(q, pl);
            res[i] = firstLower == 0 ? 0 : bestBefore.get(firstLower);
            i++;
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] items = new int[][]{
                {1,2},
                {3,2},
                {2,4},
                {5,6},
                {3,5}
        };
        int[] queries = new int[]{1,2,3,4,5,6};
        int[] res = s.maximumBeauty(items, queries);
        System.out.println(Arrays.toString(res));
    }
}
