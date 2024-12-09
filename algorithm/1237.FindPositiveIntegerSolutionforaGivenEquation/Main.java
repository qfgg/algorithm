import java.util.*;

class Solution {
    public int binarySearch(CustomFunction customfunction, int x, int maxy, int z) {
        int l = 1, r = maxy, m, attempt;
        while (l < r) {
            m = l + ((r - l) >> 1);
            attempt = customfunction.f(x, m);
            if (attempt == z) {
                return m;
            }
            if (attempt > z) {
                r = m;
            } else {
                l = l + 1;
            }
        }
        if (customfunction.f(x, l) == z) {
            return l;
        }
        return - 1;
    }
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> res = new ArrayList<>();
        int y, maxy = 1000;
        for (int i = 1; i <= 1000; i++) {
            y = binarySearch(customfunction, i, maxy, z);
            if (y != -1) {
                List<Integer> pair = new ArrayList<>(Arrays.asList(i, y));
                res.add(pair);
                maxy = y;
            }
        }
        return res;
    }
}
