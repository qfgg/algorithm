import java.util.*;

class Main {
    public static double angleClock(int hour, int minutes) {
        double h = hour;
        double m = minutes;
        double mDeg = m * 6;
        double hDeg = h * 30 + m / 2;
        double diff = Math.abs(hDeg - mDeg);
        return diff > 180 ? 360 - diff : diff;
    }

    public static void main(String[] args) {
        double ret = angleClock(12, 0);
        System.out.println(ret);
    }
}
