import java.util.*;

class TopVotedCandidate {
    private int[] ts;
    private Map<Integer, Integer> cnt;
    private Map<Integer, int[]> timeTops;
    public TopVotedCandidate(int[] persons, int[] times) {
        ts = times;
        cnt = new HashMap<>();
        timeTops = new HashMap();
        int i, n = persons.length, update;
        cnt.put(persons[0], 1);
        timeTops.put(times[0], new int[]{persons[0], 1});
        int[] lastTop;
        for (i = 1; i < n; i++) {
            lastTop = timeTops.get(times[i - 1]);
            update = cnt.getOrDefault(persons[i], 0) + 1;
            cnt.put(persons[i], update);
            if (update >= lastTop[1]) {
                timeTops.put(times[i], new int[]{persons[i], update});
            } else {
                timeTops.put(times[i], lastTop);
            }
        }
    }

    public int q(int t) {
        int l = 0, r = ts.length, m;
        while (l < r) {
            m = l + ((r - l) >> 1);
            if (t == ts[m]) {
                return timeTops.get(ts[m])[0];
            }
            if (t < ts[m]) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return timeTops.get(ts[l - 1])[0];
    }
}
public class Main {
    public static void main(String[] args) {
        TopVotedCandidate topVotedCandidate = new TopVotedCandidate(
                new int[]{0, 1, 1, 0, 0, 1, 0},
                new int[]{0, 5, 10, 15, 20, 25, 30}
        );
        System.out.println(topVotedCandidate.q(3)); // return 0, At time 3, the votes are [0], and 0 is leading.
        System.out.println(topVotedCandidate.q(12)); // return 1, At time 12, the votes are [0,1,1], and 1 is leading.
        System.out.println(topVotedCandidate.q(25)); // return 1, At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
        System.out.println(topVotedCandidate.q(15)); // return 0
        System.out.println(topVotedCandidate.q(24)); // return 0
        System.out.println(topVotedCandidate.q(8)); // return 1
    }
}
