import java.util.*;


public class Main {
    public static String sortStr(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        HashMap<String, List<String>> agMap = new HashMap<>();
        String sortedStr;
        for (String s : strs) {
            sortedStr = sortStr(s);
            if (agMap.containsKey(sortedStr)) {
                agMap.get(sortedStr).add(s);
            } else {
                List<String> l = new ArrayList<>();
                l.add(s);
                agMap.put(sortedStr, l);
                ans.add(l);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"eat","tea","tan","ate","nat","bat"};
        List<List<String>> ans = groupAnagrams(strs);
        System.out.println(Arrays.toString(ans.toArray()));
    }
}
