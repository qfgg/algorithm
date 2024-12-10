import java.util.*;


public class Main {
    public static boolean differByOne(String[] dict) {
        Set<String> tmpl = new HashSet<>();
        String cur;
        StringBuilder sb = new StringBuilder();
        int n = dict[0].length(), i;
        for (String s : dict) {
            for (i = 0; i < n; i++) {
                if (i > 0) {
                    sb.append(s, 0, i);
                }
                sb.append('*');
                if (i < n - 1) {
                    sb.append(s, i + 1, n);
                }
                cur = sb.toString();
                sb.setLength(0);
                if (tmpl.contains(cur)) {
                    return true;
                }
                tmpl.add(cur);
            }
        }
        return false;
    }
    public static void main(String[] args) {
        String[] dict0 = new String[]{"abcd","acbd", "aacd"};
        boolean res0 = differByOne(dict0);
        String[] dict1 = new String[]{"ab","cd","yz"};
        boolean res1 = differByOne(dict1);
        String[] dict2 = new String[]{"abcd","cccc","abyd","abab"};
        boolean res2 = differByOne(dict2);
        System.out.println(res0);
        System.out.println(res1);
        System.out.println(res2);
    }
}
