import java.util.*;

class NumArray {
    private int[] ns;
    private int[] d;

    private void build(int[] nums, int i, int l, int r) {
        if (l == r) {
            d[i] = nums[l];
            return;
        }
        int m = l + ((r - l) >> 1);
        build(nums, i * 2 + 1, l, m);
        build(nums, i * 2 + 2,m + 1, r);
        d[i] = d[i * 2 + 1] + d[i * 2 + 2];
    }
    public NumArray(int[] nums) {
        ns = nums;
        int n = nums.length * 4;
        d = new int[n];
        build(nums, 0, 0, nums.length - 1);
    }

    private void update(int index, int val, int i, int l, int r) {
        if (l == r) {
            if (l == index) {
                d[i] = val;
            }
            return;
        }
        int m = l + ((r - l) >> 1);
        if (index <= m) {
            update(index, val, i * 2 + 1, l, m);
        }
        if (index > m) {
            update(index, val, i * 2 + 2, m + 1, r);
        }
        d[i] = d[i * 2 + 1] + d[i * 2 + 2];
    }

    public void update(int index, int val) {
        update(index, val, 0, 0, ns.length - 1);
    }

    private int sum(int left, int right, int i, int l, int r) {
        if (left <= l && r <= right) {
            return d[i];
        }
        int m = l + ((r - l) >> 1);
        int sum = 0;
        if (left <= m) {
            sum += sum(left, right, i * 2 + 1, l, m);
        }
        if (right > m) {
            sum += sum(left, right, i * 2 + 2, m + 1, r);
        }
        return sum;
    }

    public int sumRange(int left, int right) {
        return sum(left, right, 0, 0, ns.length - 1);
    }
}
public class Main {
    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{1, 3, 5});
        System.out.println(numArray.sumRange(0, 2)); // return 1 + 3 + 5 = 9
        numArray.update(1, 2);   // nums = [1, 2, 5]
        System.out.println(numArray.sumRange(0, 2)); // return 1 + 2 + 5 = 8
    }
}
