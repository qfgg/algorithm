import java.util.*;


class Main {
    public static String strWithout3a3b(int a, int b) {
        int[] count = new int[]{a, b};
        String[][] parts = new String[][]{{"a", "aa"},{"b", "bb"}};
        int pre = -1, cur, curCount;
        StringBuilder sb = new StringBuilder();
        while (count[0] > 0 || count[1] > 0) {
            curCount = 2;
            if (count[0] == count[1]) {
                cur = pre == 0 ? 1 : 0;
            } else {
                cur = count[0] > count[1] ? 0 : 1;
                int smaller = count[0] > count[1] ? 1 : 0;
                if (pre == cur) {
                    if (count[smaller] == 0) {
                        return sb.toString();
                    }
                    cur = smaller;
                    curCount = 1;
                }
            }
            if (curCount == 2 && count[cur] >= 2) {
                sb.append(parts[cur][1]);
                count[cur] -= 2;
            } else {
                sb.append(parts[cur][0]);
                count[cur]--;
            }
            pre = cur;
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        int a = 6, b = 3;
        String ret = strWithout3a3b(a, b);
        System.out.println(ret);
    }
}
