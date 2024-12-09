import java.util.*;


public class Main {
    public static boolean takeCourses(HashMap<Integer, List<Integer>> graph, int start, Set<Integer> taken, Set<Integer> coursesInPre, int[] visited) {
        coursesInPre.remove(start);
        List<Integer> nexts;
        if (graph.containsKey(start)) {
            nexts = graph.get(start);
            taken.add(start);
            for (Integer next : nexts) {
                // 1 course cannot be taken twice, otherwise a circle exists
                if (taken.contains(next)) {
                    return false;
                }
                if (visited[next] == 0) {
                    visited[next] = 1;
                    if (!takeCourses(graph, next, taken, coursesInPre, visited)) {
                        return false;
                    }
                    // remove subtree taken courses to make sure only ancestors are to be checked
                    taken.remove(next);
                }
            }
        }
        return true;
    }
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            return true;
        }
        List<Integer> starts = new ArrayList<>();
        int preNum = prerequisites.length;
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        int[] inOutTypes = new int[numCourses];
        int[] visited = new int[numCourses];
        int i;
        for (i = 0; i < preNum; i++) {
            if (prerequisites[i][0] == prerequisites[i][1]) {
                return false;
            }
            if (inOutTypes[prerequisites[i][1]] != 2) {
                inOutTypes[prerequisites[i][1]] = 1;
            }
            inOutTypes[prerequisites[i][0]] = 2;
            if (graph.containsKey(prerequisites[i][1])) {
                graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
            } else {
                List<Integer> neighbors = new ArrayList<>();
                neighbors.add(prerequisites[i][0]);
                graph.put(prerequisites[i][1], neighbors);
            }
        }
        Set<Integer> coursesInPre = new HashSet<>();
        for (i = 0; i < numCourses; i++) {
            if (inOutTypes[i] == 1) {
                starts.add(i);
            }
            if (inOutTypes[i] != 0) {
                coursesInPre.add(i);
            }
        }
        Set<Integer> taken = new HashSet<>();
        for (Integer start : starts) {
            if (!takeCourses(graph, start, taken, coursesInPre, visited)) {
                return false;
            }
            taken.clear();
        }
        return coursesInPre.isEmpty();
    }
    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = new int[][]{{1,0},{2,1},{3,2},{3,1}};
        boolean ans = canFinish(numCourses, prerequisites);
        System.out.println(ans);
    }
}