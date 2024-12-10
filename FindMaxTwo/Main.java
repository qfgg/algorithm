import java.util.Arrays;

class Main {
    public static int[] findMaxTwo(int[] list) {
        if (list.length < 2) {
            return new int[]{-1, -1};
        }
        int max = 0, second = 1;
        if (list[0] < list[1]) {
            max = 1;
            second = 0;
        }
        for (int i = 2; i < list.length; i++) {
            if (list[i] > list[max]) {
                second = max;
                max = i;
            } else if (list[i] > list[second]) {
                second = i;
            }
        }
        return new int[]{max, second};
    }
    public static void main(String[] args) {
        int[] list = new int[]{6,3,10,4,7,7,1,8,1,8};
        System.out.println(Arrays.toString(findMaxTwo(list)));
    }
}
