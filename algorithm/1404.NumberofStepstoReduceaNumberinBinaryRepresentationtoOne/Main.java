import java.util.*;


public class Main {
    public static int numSteps(String s) {
        List<Integer> digits = new ArrayList<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                digits.add(1);
            } else {
                digits.add(0);
            }
        }
        int i = 0, j, steps = 0;
        while (i < digits.size() - 1) {
            if (digits.get(i) == 0) {
                i++;
            } else {
                j = i;
                while (j < digits.size()) {
                    if (digits.get(j) == 1) {
                        digits.set(j, 0);
                        j++;
                    } else {
                        digits.set(j, 1);
                        break;
                    }
                }
                if (j == digits.size()) {
                    digits.add(1);
                }
            }
            steps++;
        }
        return steps;
    }
    public static void main(String[] args) {
        String s = "1101";
        int ans = numSteps(s);
        System.out.println(ans);
    }
}
