import java.util.*;


class Codec {
    private static final char[] chars =
            new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private Map<String, String> urlToCode = new HashMap<>();
    private Map<String, String> codeToUrl = new HashMap<>();
    int[] id = new int[6];
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        int i = 0;
        while (i < 6) {
            if (id[i] == 61) {
                id[i] = 0;
                i++;
            } else {
                id[i]++;
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (i = 5; i >= 0; i--) {
            sb.append(chars[id[i]]);
        }
        String code = sb.toString();
        codeToUrl.put(code, longUrl);
        urlToCode.put(longUrl, code);
        return "http://tinyurl.com/" + code;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return codeToUrl.get(shortUrl.substring(19));
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
public class Main {
    public static void main(String[] args) {
    }
}
