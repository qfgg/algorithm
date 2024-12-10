
public class Main {
    public static char[] processString(String str) {
        char[] c = str.toCharArray();
        char[] res = new char[c.length * 2 + 1];
        int j = 0;
        for (int i = 0; i < res.length; i++) {
            if (i % 2 == 0) {
                res[i] = '#';
            } else {
                res[i] = c[j];
                j++;
            }
        }
        return res;
    }

    public static String manacher(String str) {
        char[] inputStr = processString(str);
        int len = inputStr.length;
        int[] lens = new int[len];
        lens[0] = 0;
        String maxSP = "";
        int maxLen = 0;
        int curCenter = 0;
        int curRight = 0;
        int mirrorIdx;
        for (int i = 1; i < len; i++) {
            if (i <= curRight) {
                mirrorIdx = 2 * curCenter - i;
                if (i + lens[mirrorIdx] < curRight) {
                    lens[i] = lens[mirrorIdx];
                } else if (i + lens[mirrorIdx] > curRight) {
                    lens[i] = curRight - i;
                } else {
                    lens[i] = curRight - i;
                    int r = curRight + 1;
                    int l = 2 * i - r;
                    while (l >= 0 && r < len && inputStr[l] == inputStr[r]) {
                        lens[i]++;
                        if (lens[i] > maxLen) {
                            maxLen = lens[i];
                            int realL = inputStr[l] == '#' ? l / 2 : (l - 1) / 2;
                            int realR = inputStr[r] == '#' ? (r - 2) / 2 : (r - 1) / 2;
                            maxSP = str.substring(realL, realR + 1);
                        }
                        if (i + lens[i] > curRight) {
                            curCenter = i;
                            curRight = i + lens[i];
                        }
                        l--;
                        r++;
                    }
                }
            } else {
                int l = i - 1;
                int r = i + 1;
                lens[i] = 0;
                while (l >= 0 && r < len && inputStr[l] == inputStr[r]) {
                    lens[i]++;
                    if (lens[i] > maxLen) {
                        maxLen = lens[i];
                        int realL = inputStr[l] == '#' ? l / 2 : (l - 1) / 2;
                        int realR = inputStr[r] == '#' ? (r - 2) / 2 : (r - 1) / 2;
                        maxSP = str.substring(realL, realR + 1);
                    }
                    if (i + lens[i] > curRight) {
                        curCenter = i;
                        curRight = i + lens[i];
                    }
                    l--;
                    r++;
                }
            }
        }
        return maxSP;
    }

    public static void main(String[] args) {
        String maxSP = manacher("abcdcbabcdxdcbabcdxdcb");
        System.out.println(maxSP);
    }
}
