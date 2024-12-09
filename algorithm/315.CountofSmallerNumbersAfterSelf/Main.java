import java.util.*;


public class Main {
  public static void merge(int[] nums, int[] idx, List<Integer> res, int s, int m, int e) {
    int[] tmp = new int[e - s + 1];
    int i = s, j = m + 1, k = 0;
    while (i <= m && j <= e) {
      if (nums[idx[i]] > nums[idx[j]]) {
        tmp[k] = idx[i];
        res.set(idx[i], res.get(idx[i]) + e - j + 1);
        i++;
        k++;
      } else {
        tmp[k] = idx[j];
        j++;
        k++;
      }
    }
    while (i <= m) {
      tmp[k] = idx[i];
      i++;
      k++;
    }
    while (j <= e) {
      tmp[k] = idx[j];
      j++;
      k++;
    }
    for (i = s; i <= e; i++) {
      idx[i] = tmp[i - s];
    }
  }
  public static void mergeSort(int[] nums, int[] idx, List<Integer> res, int s, int e) {
    if (s == e) {
      return;
    }
    int m = s + ((e - s) >> 1);
    mergeSort(nums, idx, res, s, m);
    mergeSort(nums, idx, res, m + 1, e);
    merge(nums, idx, res, s, m, e);
  }
  public static List<Integer> countSmaller(int[] nums) {
    List<Integer> res = new ArrayList<>();
    int n = nums.length, i;
    int[] idx = new int[n];
    for (i = 0; i < n; i++) {
      idx[i] = i;
      res.add(0);
    }
    mergeSort(nums, idx, res, 0, n - 1);
    return res;
  }
  public static void main(String[] args) {
    int[] nums = new int[10];
    Random r = new Random();
    for (int i = 0; i < 10; i++) {
      nums[i] = r.nextInt(10);
    }
    System.out.println(Arrays.toString(nums));
    List<Integer> res = countSmaller(nums);
    System.out.println(Arrays.toString(res.toArray()));
  }
}
