import java.util.*;

class RLEIterator {
    Deque<int[]> q;
    public RLEIterator(int[] encoding) {
        q = new ArrayDeque<>();
        int i, l = encoding.length;
        for (i = 0; i < l; i = i + 2) {
            if (encoding[i] == 0) {
                continue;
            }
            int[] pre = q.peekLast();
            if (pre == null || encoding[i + 1] != pre[1]) {
                q.add(new int[]{encoding[i], encoding[i + 1]});
            } else {
                pre[0] += encoding[i];
            }
        }
    }

    public int next(int n) {
        if (q.isEmpty()) {
            return -1;
        }
        int[] first = q.peek();
        if (n == first[0]) {
            q.removeFirst();
            return first[1];
        }
        if (n < first[0]) {
            first[0] -= n;
            return first[1];
        }
        q.removeFirst();
        return next(n - first[0]);
    }
}
public class Main {
    public static void main(String[] args) {
        RLEIterator rLEIterator = new RLEIterator(new int[]{3, 8, 0, 9, 2, 5}); // This maps to the sequence [8,8,8,5,5].
        System.out.println(rLEIterator.next(2)); // exhausts 2 terms of the sequence, returning 8. The remaining sequence is now [8, 5, 5].
        System.out.println(rLEIterator.next(1)); // exhausts 1 term of the sequence, returning 8. The remaining sequence is now [5, 5].
        System.out.println(rLEIterator.next(1)); // exhausts 1 term of the sequence, returning 5. The remaining sequence is now [5].
        System.out.println(rLEIterator.next(2)); // exhausts 2 terms, returning -1.
    }
}
