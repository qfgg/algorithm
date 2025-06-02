import java.util.*;


class MinStack {
    int[] st;
    int[] mins;
    int stTop, minsTop;
    public MinStack() {
        st = new int[30000];
        mins = new int[30000];
        stTop = -1;
        minsTop = -1;
    }

    public void push(int val) {
        stTop++;
        st[stTop] = val;
        if (minsTop == -1 || val <= st[mins[minsTop]]) {
            minsTop++;
            mins[minsTop] = stTop;
        }
    }

    public void pop() {
        if (mins[minsTop] == stTop) {
            minsTop--;
        }
        stTop--;
    }

    public int top() {
        return st[stTop];
    }

    public int getMin() {
        return st[mins[minsTop]];
    }
}
public class Main {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // return -3
        minStack.pop();
        System.out.println(minStack.top());    // return 0
        System.out.println(minStack.getMin()); // return -2
    }
}
