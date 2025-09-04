import java.util.*;


class Solution {
    void addEdge(Map<String, Map<String, Double>> edge, String from, String to, double value) {
        Map<String, Double> map;
        if (edge.containsKey(from)) {
            map = edge.get(from);
        } else {
            map = new HashMap<>();
            edge.put(from, map);
        }
        map.put(to, value);
    }
    double dfs(Map<String, Map<String, Double>> edge, String from, String to, double val, Set<String> visited) {
        if (from.equals(to)) {
            return val;
        }
        Map<String, Double> nb = edge.get(from);
        double res;
        for (String key : nb.keySet()) {
            if (!visited.contains(key)) {
                visited.add(key);
                res = dfs(edge, key, to, val * nb.get(key), visited);
                visited.remove(key);
                if (res != - 1) {
                    return res;
                }
            }
        }
        visited.remove(from);
        return -1;
    }
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Set<String> var = new HashSet<>();
        Map<String, Map<String, Double>> edge = new HashMap<>();
        int n = values.length, i;
        List<String> equation;
        String v1, v2;
        for (i = 0; i < n; i++) {
            equation = equations.get(i);
            v1 = equation.get(0);
            v2 = equation.get(1);
            var.add(v1);
            var.add(v2);
            addEdge(edge, v1, v2, values[i]);
            addEdge(edge, v2, v1, 1 / values[i]);
        }
        n = queries.size();
        double[] res = new double[n];
        Set<String> visited = new HashSet<>();
        for (i = 0; i < n; i++) {
            equation = queries.get(i);
            v1 = equation.get(0);
            v2 = equation.get(1);
            if (!var.contains(v1) || !var.contains(v2)) {
                res[i] = -1.0;
            } else {
                if (v1.equals(v2)) {
                    res[i] = 1.0;
                } else {
                    Map<String, Double> map = edge.get(v1);
                    if (map.containsKey(v2)) {
                        res[i] = map.get(v2);
                    } else {
                        visited.add(v1);
                        res[i] = dfs(edge, v1, v2, 1, visited);
                        visited.remove(v1);
                    }
                }
            }
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<List<String>> equations = new ArrayList<>();
        double[] values = new double[]{3.0,4.0,5.0,6.0};
        List<List<String>> queries = new ArrayList<>();
        equations.add(new ArrayList<>(List.of("x1","x2")));
        equations.add(new ArrayList<>(List.of("x2","x3")));
        equations.add(new ArrayList<>(List.of("x3","x4")));
        equations.add(new ArrayList<>(List.of("x4","x5")));
        queries.add(new ArrayList<>(List.of("x5","x2")));
        queries.add(new ArrayList<>(List.of("x2","x4")));
        double[] res = s.calcEquation(equations, values, queries);
        System.out.println(Arrays.toString(res));
    }
}
