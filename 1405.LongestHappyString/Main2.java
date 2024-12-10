import java.util.*;


class Main {
    public static String longestDiverseString(int a, int b, int c) {
        StringBuilder ret = new StringBuilder("");
        int[] count = new int[]{a, b, c};
        String[][] units = new String[][]{{"a", "b", "c"}, {"aa", "bb", "cc"}};
        int max, second, pre = -1;
        while (true) {
            if (count[0] > count[1]) {
                max = 0;
                second = 1;
            } else {
                max = 1;
                second = 0;
            }
            if (count[2] > count[max]) {
                second = max;
                max = 2;
            } else if (count[2] > count[second]) {
                second = 2;
            }
            if (count[max] == 0) {
                break;
            }
            if (max == pre && count[max] == count[second]) {
                int tmp = max;
                max = second;
                second = tmp;
            }
            if (max == pre) {
                if (count[second] == 0) {
                    break;
                }
                ret.append(units[0][second]);
                count[second]--;
                pre = second;
            } else {
                if (count[max] >= 2) {
                    ret.append(units[1][max]);
                    count[max] -= 2;
                } else {
                    ret.append(units[0][max]);
                    count[max]--;
                }
                pre = max;
            }
        }
        return ret.toString();
    }
    public static void main(String[] args) {
        System.out.println(longestDiverseString(0,8, 11));
    }
}
