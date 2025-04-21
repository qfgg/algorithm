import java.util.*;

class Solution {
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int[] d = new int[4];
        int i = 0;
        while (num > 0) {
            d[i] = num % 10;
            num = num / 10;
            i++;
        }
        i--;
        while (i >= 0) {
            if (i == 3) {
                while (d[i] > 0) {
                    sb.append('M');
                    d[i]--;
                }
            } else if (i == 2) {
                if (d[i] == 9) {
                    sb.append("CM");
                } else if (d[i] == 4) {
                    sb.append("CD");
                } else if (d[i] > 0 && d[i] < 4) {
                    while (d[i] > 0) {
                        sb.append('C');
                        d[i]--;
                    }
                } else if (d[i] >= 5) {
                    sb.append('D');
                    while (d[i] > 5) {
                        sb.append('C');
                        d[i]--;
                    }
                }
            } else if (i == 1) {
                if (d[i] == 9) {
                    sb.append("XC");
                } else if (d[i] == 4) {
                    sb.append("XL");
                } else if (d[i] > 0 && d[i] < 4) {
                    while (d[i] > 0) {
                        sb.append('X');
                        d[i]--;
                    }
                } else if (d[i] >= 5) {
                    sb.append('L');
                    while (d[i] > 5) {
                        sb.append('X');
                        d[i]--;
                    }
                }
            } else {
                if (d[i] == 9) {
                    sb.append("IX");
                } else if (d[i] == 4) {
                    sb.append("IV");
                } else if (d[i] > 0 && d[i] < 4) {
                    while (d[i] > 0) {
                        sb.append('I');
                        d[i]--;
                    }
                } else if (d[i] >= 5) {
                    sb.append('V');
                    while (d[i] > 5) {
                        sb.append('I');
                        d[i]--;
                    }
                }
            }
            i--;
        }
        return sb.toString();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int nums = 3593;  // MMMDXCIII
        String res = s.intToRoman(nums);
        System.out.println(res);
    }
}
