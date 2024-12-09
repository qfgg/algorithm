import java.util.*;


public class Main {
    public static int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int pl = properties.length, cnt = 0;
        Deque<int[]> sk = new ArrayDeque<>();
        int[] top;
        for (int i = 0; i < pl; i++) {
            if (sk.isEmpty()) {
                sk.push(properties[i]);
            } else {
                top = sk.peek();
                while (top != null && top[0] < properties[i][0] && top[1] < properties[i][1]) {
                    sk.pop();
                    cnt++;
                    top = sk.peek();
                }
                sk.push(properties[i]);
            }
        }
        return cnt;
    }
    public static void main(String[] args) {
        int[][] properties = new int[][]{{1,1},{2,1},{2,2},{1,2}};
        int ans = numberOfWeakCharacters(properties);
        System.out.println(ans);
    }
}
