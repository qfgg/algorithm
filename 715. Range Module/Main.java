import java.util.*;


class RangeModule {
    TreeMap<Integer, Integer> intervals;

    public RangeModule() {
        intervals = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        Integer rightLower = intervals.lowerKey(right);
        if (rightLower == null) {
            intervals.put(left, right);
        } else {
            Integer end = intervals.get(rightLower), pre = rightLower;
            if (left >= rightLower && right <= end) {
                return;
            }
            int newLeft = left, newRight = Math.max(end, right);
            while (pre != null && pre >= left) {
                intervals.remove(pre);
                pre = intervals.lowerKey(pre);
            }
            if (pre != null && intervals.get(pre) > left) {
                intervals.remove(pre);
                newLeft = pre;
            }
            Integer lnb = intervals.lowerKey(newLeft), rnb = intervals.ceilingKey(newRight);
            if (lnb != null && intervals.get(lnb) == newLeft) {
                intervals.put(lnb, newRight);
                if (rnb != null && rnb == newRight) {
                    intervals.put(lnb, intervals.get(rnb));
                    intervals.remove(rnb);
                }
                return;
            }
            if (rnb != null && rnb == newRight) {
                intervals.put(newLeft, intervals.get(rnb));
                intervals.remove(rnb);
                return;
            }
            intervals.put(newLeft, newRight);
        }
    }

    public boolean queryRange(int left, int right) {
        Integer leftLower = intervals.floorKey(left);
        return leftLower != null && intervals.get(leftLower) >= right;
    }

    public void removeRange(int left, int right) {
        Integer rightLower = intervals.lowerKey(right);
        if (rightLower == null) {
            return;
        }
        Integer end = intervals.get(rightLower), pre = rightLower;
        if (left >= rightLower && right <= end) {
            intervals.remove(rightLower);
            if (left > rightLower) {
                intervals.put(rightLower, left);
            }
            if (right < end) {
                intervals.put(right, end);
            }
            return;
        }
        if (rightLower >= left && end > right) {
            intervals.remove(rightLower);
            intervals.put(right, end);
            pre = intervals.lowerKey(rightLower);
        }
        while (pre != null && pre >= left) {
            intervals.remove(pre);
            pre = intervals.lowerKey(pre);
        }
        if (pre != null && intervals.get(pre) > left) {
            intervals.put(pre, left);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        RangeModule rangeModule = new RangeModule();
        rangeModule.addRange(6, 8);
        rangeModule.removeRange(7, 8);
        rangeModule.removeRange(8, 9);
        rangeModule.addRange(8, 9);
        rangeModule.removeRange(1, 3);
        rangeModule.addRange(1, 8);
        System.out.println(rangeModule.queryRange(2, 4));
        System.out.println(rangeModule.queryRange(2, 9));
        System.out.println(rangeModule.queryRange(4, 6));
    }
}
