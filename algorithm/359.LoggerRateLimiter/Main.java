import java.util.*;

class Logger {
    HashMap<String, Integer> msgTimeMap;

    Logger() {
        msgTimeMap = new HashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        if (msgTimeMap.containsKey(message) &&
                timestamp - msgTimeMap.get(message) < 10) {
            return false;
        }
        msgTimeMap.put(message, timestamp);
        return true;
    }
}
public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger();
        System.out.println(
                logger.shouldPrintMessage(1, "foo")
        );
//      returns true;
        System.out.println(
                logger.shouldPrintMessage(2,"bar")
        );
//       returns true;
        System.out.println(
                logger.shouldPrintMessage(3,"foo")
        );
//        returns false;
        System.out.println(
                logger.shouldPrintMessage(8,"bar")
        );
//        returns false;
        System.out.println(
                logger.shouldPrintMessage(10,"foo")
        );
//        returns false;
        System.out.println(
                logger.shouldPrintMessage(11,"foo")
        );
//        returns true;
    }
}
