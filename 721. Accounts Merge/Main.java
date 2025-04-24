import java.util.*;


class UF {
    int[] f;
    int[] size;
    UF(int n) {
        f = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = i;
            size[i] = 1;
        }
    }
    int find(int x) {
        if (f[x] == x) {
            return x;
        }
        int r = find(f[x]);
        f[x] = r;
        return r;
    }
    void union(int x, int y) {
        int r1 = find(x);
        int r2 = find(y);
        if (r1 == r2) {
            return;
        }
        if (size[r1] < size[r2]) {
            f[r1] = r2;
            size[r2] = size[r1] + size[r2];
        } else {
            f[r2] = r1;
            size[r1] = size[r1] + size[r2];
        }
    }
}
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String, Integer> accMap = new HashMap<>();
        int len = accounts.size(), i, j, src, size;
        String email;
        UF uf = new UF(len);
        List<String> emails;
        for (i = 0; i < len; i++) {
            emails = accounts.get(i);
            size = emails.size();
            for (j = 1; j < size; j++) {
                email = emails.get(j);
                if (accMap.containsKey(email)) {
                    src = accMap.get(email);
                    uf.union(src, i);
                } else {
                    accMap.put(email, i);
                }
            }
        }
        HashMap<Integer, List<String>> map = new HashMap<>();
        List<String> l;
        for (i = 0; i < len; i++) {
            src = uf.find(i);
            List<String> ac = accounts.get(i);
            if (map.containsKey(src)) {
                l = map.get(src);
            } else {
                l = new ArrayList<>();
                map.put(src, l);
                l.add(ac.get(0));
            }
            size = ac.size();
            for (j = 1; j < size; j++) {
                email = ac.get(j);
                if (accMap.containsKey(email)) {
                    l.add(email);
                    accMap.remove(email);
                }
            }
        }
        for (Integer key : map.keySet()) {
            l = map.get(key);
            l.subList(1, l.size()).sort(null);
            res.add(l);
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(new ArrayList<>(Arrays.asList("David","David0@m.co","David1@m.co")));
        accounts.add(new ArrayList<>(Arrays.asList("David","David3@m.co","David4@m.co")));
        accounts.add(new ArrayList<>(Arrays.asList("David","David4@m.co","David5@m.co")));
        accounts.add(new ArrayList<>(Arrays.asList("David","David2@m.co","David3@m.co")));
        accounts.add(new ArrayList<>(Arrays.asList("David","David1@m.co","David2@m.co")));
        List<List<String>> res = s.accountsMerge(accounts);
        System.out.println(res);
    }
}
