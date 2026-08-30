import java.util.*;


class Solution {
    void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }
    int find(int[] nums, int start, int end, int n) {
        if (start == end) {
            return nums[start];
        }
        int p = nums[start], l = start, r = end;
        while (l < r) {
            while (l < r && nums[r] >= p) {
                r--;
            }
            while (l < r && nums[l] <= p) {
                l++;
            }
            if (l < r) {
                swap(nums, l, r);
            }
        }
        swap(nums, start, l);
        if (n == l) {
            return nums[l];
        }
        if (n > l) {
            return find(nums, l + 1, end, n);
        }
        return find(nums, start, l - 1, n);
    }
    void sort(int[] nums, double median) {
        int l, h, lr, hr, len = nums.length, i;
        if (len % 2 == 1) {
            lr = len - 1;
            hr = len - 2;
        } else {
            lr = len - 2;
            hr = len - 1;
        }
        i = lr;
        l = lr;
        h = 1;
        while (i % 2 == 0 || i >= h) {
            if (nums[i] < median) {
                swap(nums, i, l);
                i -= 2;
                l -= 2;
            } else if (nums[i] == median) {
                i -= 2;
            } else {
                swap(nums, i, h);
                h += 2;
            }
            if (i < 0) {
                i = hr;
            }
        }
    }
    public void wiggleSort(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return;
        }
        double median;
        int l, r;
        if (len % 2 == 1) {
            median = find(nums, 0, len - 1, len / 2);
        } else {
            l = find(nums, 0, len - 1, len / 2 - 1);
            r = find(nums, 0, len - 1, len / 2);
            median = (double)(l + r) / 2;
        }
        sort(nums, median);
    }
}
public class Main {
  public static void main(String[] args) {
      Solution s = new Solution();
      int[] nums = new int[]{4,5,5,6};
      s.wiggleSort(nums);
      System.out.println(Arrays.toString(nums));
  }
}
