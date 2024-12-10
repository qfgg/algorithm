import java.util.*;


class Main {
    public static String genStr(int k, int len) {
        char[] chars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Random rand = new Random();
        int i;
        StringBuilder sb = new StringBuilder();
        int j = len;
        while (j > 0) {
            i = rand.nextInt(k);
            sb.append(chars[i]);
            j--;
        }
        return sb.toString();
    }
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        int maxLen = 0, curK = 0, i = 0, j = 0, len = s.length(), countIdx, newLen;
        int[] count = new int[26];
        while (true) {
            while (j < len) {
                countIdx = s.charAt(j) - 'a';
                if (count[countIdx] == 0) {
                    if (curK == k) {
                        break;
                    }
                    curK++;
                }
                count[countIdx]++;
                j++;
                newLen = j - i;
                if (newLen > maxLen) {
                    maxLen = newLen;
                }
            }
            if (j == len) {
                break;
            }
            // move left boundary rightward to make curK = k - 1
            while (curK == k) {
                countIdx = s.charAt(i) - 'a';
                if (count[countIdx] == 1) {
                    curK--;
                }
                count[countIdx]--;
                i++;
            }
        }
        return maxLen;
    }
    public static void main(String args[]) {
        String s = genStr(3, 7);
        int ans = lengthOfLongestSubstringKDistinct(s, 2);
        System.out.println(s);
        System.out.println(ans);
    
}
