import java.util.*;


public class Main {
    public static int trap(int[] height) {
        Deque<Integer> ms = new ArrayDeque<>();
        int l = height.length, top, pre, water = 0;
        for (int i = 0; i < l; i++) {
            if (ms.isEmpty()) {
                ms.push(i);
            } else {
                top = ms.peek();
                pre = top;
                while (!ms.isEmpty() && height[i] > height[top]) {
                    ms.pop();
                    if (!ms.isEmpty()) {
                        pre = ms.peek();
                        water += (i - pre - 1) * (Math.min(height[pre], height[i]) - height[top]);
                    }
                    top = pre;
                }
                ms.push(i);
            }
        }
        return water;
    }
    public static void main(String[] args) {
        int[] height = new int[]{5,2,1,2,1,5};
        int res = trap(height);
        System.out.println(res);
    }
}
