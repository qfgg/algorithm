import java.util.*;


public class Main {
    public static void dfs(HashMap<String, PriorityQueue<String>> graph, Stack<String> record, Stack<String> reversedPath) {
        while (!record.isEmpty()) {
            String curVertex = record.peek();
            if (!graph.containsKey(curVertex)) {
                reversedPath.push(record.pop());
                continue;
            }
            PriorityQueue<String> pq = graph.get(curVertex);
            String to = pq.poll();
            if (pq.isEmpty()) {
                graph.remove(curVertex);
            }
            record.push(to);
        }
    }
    public static List<String> findItinerary(List<List<String>> tickets, String start) {
        Stack<String> record = new Stack<>();
        Stack<String> reversedPath = new Stack<>();
        HashMap<String, PriorityQueue<String>> vertexPQMap = new HashMap<String, PriorityQueue<String>>();
        for (List<String> list : tickets) {
            String from = list.get(0);
            String to = list.get(1);
            if (vertexPQMap.containsKey(from)) {
                vertexPQMap.get(from).add(to);
            } else {
                PriorityQueue<String> pq = new PriorityQueue<String>();
                pq.add(to);
                vertexPQMap.put(from, pq);
            }
        }
        record.push(start);
        dfs(vertexPQMap, record, reversedPath);

        List<String> path = new ArrayList<>();
        while(!reversedPath.isEmpty()) {
            path.add(reversedPath.pop());
        }
        return path;
    }

    public static void main(String[] args) {
        List<List<String>> input1 = Arrays.asList(
                Arrays.asList("JFK","SFO"),
                Arrays.asList("JFK","ATL"),
                Arrays.asList("SFO","ATL"),
                Arrays.asList("ATL","JFK"),
                Arrays.asList("ATL","SFO")
        );
        List<String> ret1 = findItinerary(input1, "JFK");
        System.out.println(ret1.toString());
        List<List<String>> input2 = Arrays.asList(
                Arrays.asList("1","2"),
                Arrays.asList("2","3"),
                Arrays.asList("3","4"),
                Arrays.asList("4","2"),
                Arrays.asList("5","4"),
                Arrays.asList("2","5"),
                Arrays.asList("6","5"),
                Arrays.asList("5","7"),
                Arrays.asList("4","6")
        );
        List<String> ret2 = findItinerary(input2, "1");
        System.out.println(ret2.toString());
    }
}
