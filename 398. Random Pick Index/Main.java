import java.util.*;


class Solution {
    Map<Integer, List<Integer>> idxMap;
    public Solution(int[] nums) {
        idxMap = new HashMap<>();
        List<Integer> l;
        int i, len = nums.length;
        for (i = 0; i < len; i++) {
            if (idxMap.containsKey(nums[i])) {
                l = idxMap.get(nums[i]);
            } else {
                l = new ArrayList<>();
                idxMap.put(nums[i], l);
            }
            l.add(i);
        }
    }

    public int pick(int target) {
        List<Integer> l = idxMap.get(target);
        int len = l.size();
        Random r = new Random();
        return l.get(r.nextInt(len));
    }
}
public class Main {
  public static void main(String[] args) {
      int[] nums = new int[]{1,1,2,1,2,3,2,1};
      Solution s = new Solution(nums);
      System.out.println(s.pick(2));
      System.out.println(s.pick(1));
      System.out.println(s.pick(2));
  }
}
