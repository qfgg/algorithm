import java.util.*;

class Main {
    public static int minDeletions(String s) {
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        Arrays.sort(count);
        int delCount = 0;
        int pre = Integer.MAX_VALUE;
        for (int i = 25; i >= 0; i--) {
            if (count[i] == 0) {
                continue;
            }
            if (pre == 1) {
                delCount += count[i];
            } else if (count[i] >= pre) {
                int del = count[i] - pre + 1;
                pre--;
                delCount += del;
            } else {
                pre = count[i];
            }
        }
        return delCount;
    }
    public static void main(String[] args) {
        int ret = minDeletions("odofingjfdncnecmfdekekchbbicbflkoanohamkcjljbfmahhbnkiangdal");
        System.out.println(ret);
    }
}
