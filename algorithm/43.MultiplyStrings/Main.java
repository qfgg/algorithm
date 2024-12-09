import java.util.*;


public class Main {
    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        char[] c1 = num1.toCharArray();
        char[] c2 = num2.toCharArray();
        int i, j, l1 = c1.length, l2 = c2.length, l = l1 + l2;
        int[] n1 = new int[l1];
        int[] n2 = new int[l2];
        int[] p = new int[l];
        for (i = 0 ; i < l1; i++) {
            n1[i] = c1[l1 - i - 1] - '0';
        }
        for (i = 0 ; i < l2; i++) {
            n2[i] = c2[l2 - i - 1] - '0';
        }
        for (i = 0 ; i < l1; i++) {
            for (j = 0 ; j < l2; j++) {
                p[j + i] += n1[i] * n2[j];
            }
        }
        for (i = 0; i < l; i++) {
            if (p[i] >= 10) {
                p[i + 1] += p[i] / 10;
                p[i] = p[i] % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        i = l - 1;
        while (i >= 0 && p[i] == 0) {
            i--;
        }
        while (i >= 0) {
            sb.append(p[i]);
            i--;
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String num1 = "5";
        String num2 = "12";
        String ans = multiply(num1, num2);
        System.out.println(ans);
        num1 = "987";
        num2 = "789";
        ans = multiply(num1, num2);
        System.out.println(ans); // "778743"
    }
}
