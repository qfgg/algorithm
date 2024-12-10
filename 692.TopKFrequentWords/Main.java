import java.util.*;


class FrequencyComparator implements Comparator<String> {
    Map<String, Integer> frequency;
    FrequencyComparator(Map<String, Integer> fq) {
        frequency = fq;
    }
    public int compare(String a, String b) {
        int fqCompare = frequency.get(a).compareTo(frequency.get(b));
        return fqCompare == 0 ? b.compareTo(a) : fqCompare;
    }
}
public class Main {
    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> fq = new HashMap<>();
        for (String s : words) {
            fq.merge(s, 1, Integer::sum);
        }
        Queue<String> topWords = new PriorityQueue<>(new FrequencyComparator(fq));
        List<String> ans = new ArrayList<>();
        for (String s : fq.keySet()) {
            topWords.offer(s);
            if (topWords.size() > k) {
                topWords.poll();
            }
        }
        while (!topWords.isEmpty()) {
            ans.add(0, topWords.poll());
        }
        return ans;
    }
    public static void main(String[] args) {
        String[] words = new String[]{"i","love","leetcode","i","love","coding"};
        int k = 2;
        List<String> ans = topKFrequent(words, k);
        System.out.println(ans);
    }
}
