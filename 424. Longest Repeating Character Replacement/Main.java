import java.util.*;

class Solution {
    private int replace(char[] chars, char goal, int k, int max) {
        int l = 0, r = 0, len = chars.length, replaced = 0, newMax = max;
        while (r < len) {
            if (chars[r] != goal) {
                replaced++;
                r++;
                if (replaced > k) {
                    while (chars[l] == goal) {
                        l++;
                    }
                    l++;
                    replaced--;
                }
            } else {
                r++;
            }
            newMax = Math.max(newMax, r - l);
        }
        return newMax;
    }
    public int characterReplacement(String s, int k) {
        char[] chars = s.toCharArray();
        int[] count = new int[26];
        for (char c : chars) {
            count[c - 'A']++;
        }
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> count[b] - count[a]);
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                q.add(i);
            }
        }
        int top = q.poll(), max = 1;
        while (count[top] + k > max) {
            max = replace(chars, (char)('A' + top), k, max);
            if (q.isEmpty()) {
                break;
            }
            top = q.poll();
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String str = "AABABBA";
        int res = s.characterReplacement(str, 1);
        System.out.println(res);
    }
}
