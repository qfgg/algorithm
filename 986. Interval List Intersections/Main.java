import java.util.*;


class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> itv = new ArrayList<>();
        int i, j, n1 = firstList.length, n2 = secondList.length;
        HashSet<Integer> firstIn = new HashSet<>();
        HashSet<Integer> firstOut = new HashSet<>();
        HashSet<Integer> secondIn = new HashSet<>();
        HashSet<Integer> secondOut = new HashSet<>();
        List<Integer> tl1 = new ArrayList<>();
        List<Integer> tl2 = new ArrayList<>();
        for (i = 0; i < n1; i++) {
            firstIn.add(firstList[i][0]);
            firstOut.add(firstList[i][1]);
            tl1.add(firstList[i][0]);
            tl1.add(firstList[i][1]);
        }
        for (i = 0; i < n2; i++) {
            secondIn.add(secondList[i][0]);
            secondOut.add(secondList[i][1]);
            tl2.add(secondList[i][0]);
            tl2.add(secondList[i][1]);
        }
        List<Integer> timeline = new ArrayList<>();
        i = 0;
        j = 0;
        n1 = tl1.size();
        n2 = tl2.size();
        while (i < n1 || j < n2) {
            if (i == n1) {
                timeline.add(tl2.get(j));
                j++;
            } else if (j == n2) {
                timeline.add(tl1.get(i));
                i++;
            } else {
                if (tl1.get(i).equals(tl2.get(j))) {
                    timeline.add(tl1.get(i));
                    i++;
                    j++;
                } else if (tl1.get(i) < tl2.get(j)) {
                    timeline.add(tl1.get(i));
                    i++;
                } else {
                    timeline.add(tl2.get(j));
                    j++;
                }
            }
        }
        boolean in1 = false, in2 = false;
        n1 = timeline.size();
        int t, start = -1;
        for (i = 0; i < n1; i++) {
            t = timeline.get(i);
            if (firstIn.contains(t) && secondOut.contains(t) ||
                    secondIn.contains(t) && firstOut.contains(t)) {
                itv.add(new int[]{t, t});
            }
            if (firstIn.contains(t)) {
                in1 = true;
            }
            if (secondIn.contains(t)) {
                in2 = true;
            }
            if (firstOut.contains(t)) {
                in1 = false;
                if (start != -1) {
                    itv.add(new int[]{start, t});
                    start = -1;
                }
            }
            if (secondOut.contains(t)) {
                in2 = false;
                if (start != -1) {
                    itv.add(new int[]{start, t});
                    start = -1;
                }
            }
            if (in1 && in2 && start == -1) {
                start = t;
            }
        }
        n1 = itv.size();
        int[][] res = new int[n1][];
        for (i = 0; i < n1; i++) {
            res[i] = itv.get(i);
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] firstList = new int[][]{
                {0,2},{5,10},{13,23},{24,25}
        };
        int[][] secondList = new int[][]{
                {1,5},{8,12},{15,24},{25,26}
        };
        int[][] res = s.intervalIntersection(firstList, secondList);
        System.out.println(Arrays.deepToString(res));
    }
}
