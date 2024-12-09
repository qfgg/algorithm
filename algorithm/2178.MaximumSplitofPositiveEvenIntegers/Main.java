import java.util.*;

public class Main {
    public static List<Long> maximumEvenSplit(long finalSum) {
        if (finalSum % 2 == 1) {
            return new ArrayList<>();
        }
        long i = 2, sum = 0;
        List<Long> ans = new ArrayList<>();
        while (true) {
            if (sum + i > finalSum) {
                ans.set(ans.size() - 1, ans.get(ans.size() - 1) + finalSum - sum);
                break;
            }
            ans.add(i);
            sum += i;
            i += 2;
        }
        return ans;
    }
    public static void main(String[] args) {
        long finalSum = 28;
        List<Long> ans = maximumEvenSplit(finalSum);
        System.out.println(Arrays.toString(ans.toArray()));
    }
}