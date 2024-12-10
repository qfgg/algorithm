import java.util.*;

public class Main {
    public static int twoEggDrop(int n) {
        return (int) Math.round(Math.sqrt(n * 2));
    }
    public static void main(String[] args) {
        int ans = twoEggDrop(100);
        System.out.println(ans);
    }
}
