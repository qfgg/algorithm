import java.util.*;


class Main {

    public static void dfs(List<Integer> n, int[] count, int index, int maxIndex, List<Integer> subset, List<List<Integer>> subsets) {
        if (index > maxIndex) {
            subsets.add(new ArrayList<>(subset));
            return;
        }

        int cur = n.get(index);
        for (int i = 0; i <= count[cur + 10]; i++) {
            if (i > 0) {
                subset.add(cur);
            }
            dfs(n, count, index + 1, maxIndex, subset, subsets);
        }
        int j = count[cur + 10];
        while (j > 0) {
            subset.remove(subset.size() - 1);
            j--;
        }
    }
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        int len = nums.length;
        List<Integer> n = new ArrayList<>();
        int[] count = new int[21];
        for (int num : nums) {
            int idx = num + 10;
            if (count[idx] == 0) {
                n.add(num);
                count[idx] = 1;
            } else {
                count[idx]++;
            }
        }
        List<Integer> subset = new ArrayList<>();
        List<List<Integer>> subsets = new ArrayList<>();
        dfs(n, count, 0, n.size() - 1, subset, subsets);
        return subsets;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2};
        List<List<Integer>> ret = subsetsWithDup(nums);
        System.out.println(Arrays.toString(ret.toArray()));
    }
}
