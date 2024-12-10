import java.util.*;


public class Main {
    public static int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        Stack<Integer> funcSk = new Stack<>();
        int i, len = logs.size(), id, preTimestamp, timestamp;
        String preState, state;
        String[] splits = logs.get(0).split(":");
        id = Integer.parseInt(splits[0]);
        preState = splits[1];
        preTimestamp = Integer.parseInt(splits[2]);
        funcSk.push(id);
        i = 1;
        while (i < len) {
            splits = logs.get(i).split(":");
            id = Integer.parseInt(splits[0]);
            state = splits[1];
            timestamp = Integer.parseInt(splits[2]);
            if (preState.equals("start")) {
                if (state.equals("start")) {
                    ans[funcSk.peek()] += timestamp - preTimestamp;
                    funcSk.push(id);
                } else {
                    ans[funcSk.pop()] += timestamp - preTimestamp + 1;
                }
            } else {
                if (state.equals("start")) {
                    if (!funcSk.isEmpty()) {
                        ans[funcSk.peek()] += timestamp - preTimestamp - 1;
                    }
                    funcSk.push(id);
                } else {
                    ans[funcSk.pop()] += timestamp - preTimestamp;
                }
            }
            preTimestamp = timestamp;
            preState = state;
            i++;
        }
        return ans;
    }
    public static void main(String[] args) {
        int n = 2;
        List<String> logs = new ArrayList<>(Arrays.asList(
                "0:start:0",
                "0:start:2",
                "0:end:5",
                "1:start:6",
                "1:end:6",
                "0:end:7"
        ));
        int[] ans = exclusiveTime(n, logs);
        System.out.println(Arrays.toString(ans));
    }
}
