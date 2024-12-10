import java.util.*;

public class Main {
    public static int lengthLongestPath(String input) {
        int len = 0, pos = 0, i, j, n = input.length(), level = 0, l;
        boolean isFile = false;
        List<Integer> pathlen = new ArrayList<>();
        for (i = 0; i < n; i++) {
            while (input.charAt(i) == '\t') {
                level++;
                pos++;
                i++;
            }
            if (input.charAt(i) == '.') {
                isFile = true;
            }
            if (i == n - 1 || input.charAt(i + 1) == '\n') {
                if (level >= pathlen.size()) {
                    pathlen.add(i - pos + 1);
                } else {
                    pathlen.set(level, i - pos + 1);
                }
                if (isFile) {
                    l = level;
                    for (j = 0; j <= level; j++) {
                        l += pathlen.get(j);
                    }
                    if (l > len) {
                        len = l;
                    }
                    isFile = false;
                }
                i++;
                pos = i + 1;
                level = 0;
            }
        }
        return len;
    }
    public static void main(String[] args) {
        String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        int res = lengthLongestPath(input);
        System.out.println(res);
    }
}
