import java.util.*;


class Solution {
    private List<String> split(String path) {
        List<String> res = new ArrayList<>();
        int i = 0, n = path.length(), pre = 0;
        while (i < n) {
            if ((i == n - 1 || path.charAt(i + 1) == '/') && i > 0) {
                res.add(path.substring(pre, i + 1) + '@' + path.substring(0, pre));
                pre = i + 1;
            }
            i++;
        }
        return res;
    }
    private void buildPath(String root, HashMap<String, List<String>> fmap, StringBuilder sb, List<String> res) {
        String[] parts = root.split("@");
        sb.append(parts[0]);
        List<String> subs = fmap.get(root);
        if (subs.isEmpty()) {
            res.add(sb.toString());
        } else {
            for (String sub : subs) {
                buildPath(sub, fmap, sb, res);
            }
        }
        sb.delete(sb.length() - parts[0].length(), sb.length());
    }

    public List<String> removeSubfolders(String[] folder) {
        List<String> res = new ArrayList<>(), subs, preSubs;
        HashMap<String, List<String>> fmap = new HashMap<>();
        HashSet<String> roots = new HashSet<>();
        String cur;
        int i, n;
        for (String f : folder) {
            i = 0;
            preSubs = null;
            List<String> splits = split(f);
            n = splits.size();
            while (i < n) {
                cur = splits.get(i);
                if (i == 0) {
                    roots.add(cur);
                }
                if (fmap.containsKey(cur)) {
                    subs = fmap.get(cur);
                    if (subs.isEmpty()) {
                        break;
                    }
                    if (i == n - 1) {
                        fmap.put(cur, new ArrayList<>());
                        break;
                    }
                } else {
                    if (preSubs != null) {
                        preSubs.add(cur);
                    }
                    subs = new ArrayList<>();
                    fmap.put(cur, subs);
                }
                i++;
                preSubs = subs;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String r : roots) {
            buildPath(r, fmap, sb, res);
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] folder = new String[]{"/a/abc","/b/abc/def"};
        List<String> res = s.removeSubfolders(folder);
        System.out.println(res);
    }
}
