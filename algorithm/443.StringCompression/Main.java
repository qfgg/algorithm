import java.util.*;


class Main {
    public static int compress(char[] chars) {
        int count = 1;
        int j = 1;
        char pre = chars[0];
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == pre) {
                count++;
            } else {
                if (count >= 10) {
                    char[] countChars = Integer.toString(count).toCharArray();
                    for (char c : countChars) {
                        chars[j++] = c;
                    }
                } else if (count > 1) {
                    chars[j++] = Character.forDigit(count, 10);
                }
                chars[j++] = chars[i];
                pre = chars[i];
                count = 1;
            }
        }
        if (count >= 10) {
            char[] countChars = Integer.toString(count).toCharArray();
            for (char c : countChars) {
                chars[j++] = c;
            }
        } else if (count > 1) {
            chars[j++] = Character.forDigit(count, 10);
        }
        return j;
    }

    public static void main(String[] args) {
        char[] chars = new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        int ans = compress(chars);
        System.out.println(ans);
    }
}
