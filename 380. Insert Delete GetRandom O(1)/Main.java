import java.util.*;

class RandomizedSet {
    HashMap<Integer, Integer> map;
    int[] arr;
    int tail ;
    Random rand;

    public RandomizedSet() {
        map = new HashMap<>();
        arr = new int[20001];
        tail = 0;
        rand = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, tail);
        arr[tail] = val;
        tail++;
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int idx = map.get(val);
        if (idx != tail - 1) {
            arr[idx] = arr[tail - 1];
            map.put(arr[tail - 1], idx);
        }
        tail--;
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return arr[rand.nextInt(tail)];
    }
}
class Solution {

}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.insert(0));
        System.out.println(randomizedSet.insert(1));
        System.out.println(randomizedSet.remove(0));
        System.out.println(randomizedSet.insert(2));
        System.out.println(randomizedSet.remove(1));
        System.out.println(randomizedSet.getRandom());
    }
}
