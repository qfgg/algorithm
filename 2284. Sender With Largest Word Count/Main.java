import java.util.*;


class Solution {
    private int countWords(String msg) {
        int count = 1, n = msg.length(), i;
        for (i = 0; i < n; i++) {
            if (msg.charAt(i) == ' ') {
                count++;
            }
        }
        return count;
    }
    public String largestWordCount(String[] messages, String[] senders) {
        HashMap<String, Integer> count = new HashMap<>();
        int n = messages.length, i, curCount, max = 0;
        String res = "";
        for (i = 0; i < n; i++) {
            curCount = count.getOrDefault(senders[i],  0) + countWords(messages[i]);
            if (curCount > max) {
                max = curCount;
                res = senders[i];
            } else if (curCount == max && senders[i].compareTo(res) > 0) {
                res = senders[i];
            }
            count.put(senders[i], curCount);
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] messages = new String[]{
                "Hello userTwooo","Hi userThree","Wonderful day Alice","Nice day userThree"
        };
        String[] senders = new String[]{
                "Alice","userTwo","userThree","Alice"
        };
        String res = s.largestWordCount(messages, senders);
        System.out.println(res);
    }
}
