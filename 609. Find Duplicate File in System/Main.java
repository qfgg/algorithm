import java.util.*;


class Solution {
    void parsePath(String path, Map<String, List<String>> content2files) {
        String[] paths = path.split(" ");
        String content;
        List<String> files;
        int i, n = paths.length;
        for (i = 1; i < n; i++) {
            String[] seg = paths[i].split("\\(");
            content = seg[1].substring(0, seg[1].length() - 1);
            if (content2files.containsKey(content)) {
                files = content2files.get(content);
            } else {
                files = new ArrayList<>();
                content2files.put(content, files);
            }
            files.add(paths[0] + "/" + seg[0]);
        }
    }
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> content2files = new HashMap<>();
        for (String path : paths) {
            parsePath(path, content2files);
        }
        List<List<String>> res = new ArrayList<>();
        List<String> files;
        for (String key : content2files.keySet()) {
            files = content2files.get(key);
            if (files.size() > 1) {
                res.add(files);
            }
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] paths = new String[]{
                "root/a 1.txt(abcd) 2.txt(efgh)",
                "root/c 3.txt(abcd)",
                "root/c/d 4.txt(efgh)",
                "root 4.txt(efgh)"
        };
        List<List<String>> res = s.findDuplicate(paths);
        System.out.println(res);
    }
}
