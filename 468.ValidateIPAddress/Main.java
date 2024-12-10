import java.util.*;


public class Main {
    public static String validIPv4(String queryIP) {
        String[] parts = queryIP.split("\\.");
        int partLen;
        char c1, c2, c3;
        if (parts.length != 4) {
            return "Neither";
        }
        for (String part: parts) {
            partLen = part.length();
            if (partLen < 1 || partLen > 3) {
                return "Neither";
            }
            c1 = part.charAt(0);
            if (partLen == 1 && (c1 < '0' || c1 > '9')) {
                return "Neither";
            } else if (partLen == 2) {
                c2 = part.charAt(1);
                if (c1 < '1' || c1 > '9' || c2 < '0' || c2 > '9') {
                    return "Neither";
                }
            } else if (partLen == 3) {
                c2 = part.charAt(1);
                c3 = part.charAt(2);
                if (c1 < '1' || c1 > '2') {
                    return "Neither";
                }
                if (c1 == '1' && (c2 < '0' || c2 > '9' || c3 < '0' || c3 > '9')) {
                    return "Neither";
                }
                if (c1 == '2' && (c2 < '0' || c2 > '5' ||
                        (c2 == '5' && (c3 < '0' || c3 > '5')) ||
                        (c3 < '0' || c3 > '9'))) {
                    return "Neither";
                }
            }
        }
        return "IPv4";
    }
    public static String validIPv6(String queryIP) {
        String[] parts = queryIP.split(":");
        int partLen, i;
        char cur;
        if (parts.length != 8) {
            return "Neither";
        }
        for (String part: parts) {
            partLen = part.length();
            if (partLen < 1 || partLen > 4) {
                return "Neither";
            }
            for (i = 0; i < partLen; i++) {
                cur = part.charAt(i);
                if ((cur < '0' || cur > '9') &&
                        (cur < 'a' || cur > 'f') &&
                        (cur < 'A' || cur > 'F')) {
                    return "Neither";
                }
            }
        }
        return "IPv6";
    }
    public static String validIPAddress(String queryIP) {
        int i = 0, len = queryIP.length();
        if (len == 0) {
            return "Neither";
        }
        char lastChar = queryIP.charAt(len - 1);
        if (lastChar == '.' || lastChar == ':') {
            return "Neither";
        }
        while (i < len) {
            if (queryIP.charAt(i) == '.') {
                return validIPv4(queryIP);
            } else if (queryIP.charAt(i) == ':') {
                return validIPv6(queryIP);
            }
            i++;
        }
        return "Neither";
    }
    public static void main(String[] args) {
        String ans = validIPAddress("172.16.254.1");
        System.out.println(ans);
        ans = validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334");
        System.out.println(ans);
        ans = validIPAddress("256.256.256.256");
        System.out.println(ans);
        ans = validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:");
        System.out.println(ans);
    }
}
