import java.util.*;


public class Main {
    public static void move(boolean isForward, int n, int r, int cur, int offset, int[] f, int[] dir, Queue<Integer> q) {
        int nextPos = isForward ? cur + offset : cur - offset, nextDist;
        if (nextPos > 1 && nextPos < n ) {
            nextDist = f[cur] + r + f[offset];
            if (dir[nextPos] == 0 || nextDist < f[nextPos]) {
                f[nextPos] = nextDist;
                dir[nextPos] = isForward ? 1 : 2;
                q.add(nextPos);
            } else if (nextDist == f[nextPos]) {
                dir[nextPos] |= isForward ? 1 : 2;
                q.add(nextPos);
            }
        }
    }
    public static int racecar(int target) {
        if (target == 1) {
            return 1;
        }
        List<Integer> pos = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        int n = 1;
        while (n <= target) {
            n = n << 1;
            pos.add(n - 1);
            q.add(n - 1);
        }
        int[] f = new int[n];
        int[] dir = new int[n];
        int l = pos.size(), cur, i;
        for (i = l - 1; i >= 0; i--) {
            f[pos.get(i)] = i + 1;
            dir[pos.get(i)] = 1;
        }
        while (!q.isEmpty()) {
            cur = q.poll();
            for (i = l - 1; i >= 0; i--) {
                if (dir[cur] == 3) {
                    move(false, n, 1, cur, pos.get(i), f, dir, q);
                    move(true, n, 1, cur, pos.get(i), f, dir, q);
                } else if (dir[cur] == 1) {
                    move(false, n, 1, cur, pos.get(i), f, dir, q);
                    move(true, n, 2, cur, pos.get(i), f, dir, q);
                } else if (dir[cur] == 2) {
                    move(false, n, 2, cur, pos.get(i), f, dir, q);
                    move(true, n, 1, cur, pos.get(i), f, dir, q);
                }
            }
        }
        return f[target];
    }
    public static void main(String[] args) {
        int target = 27;
        int res = racecar(target);
        System.out.println(res);
    }
}
