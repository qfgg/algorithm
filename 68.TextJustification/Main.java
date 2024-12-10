import java.util.*;


public class Main {
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String space = " ";
        int l = words.length, i = 0, j = 0, lineL = 0, wLen = 0, q, extra;
        while(i < l) {
            while (j < l) {
                if (j > i) {
                    lineL++;
                }
                lineL += words[j].length();
                if (lineL > maxWidth) {
                    break;
                }
                wLen += words[j].length();
                j++;
            }
            j--;
            q = i == j ? 0 : (maxWidth - wLen) / (j - i);
            extra = i == j ? maxWidth - wLen : (maxWidth - wLen) % (j - i);
            if (i == j) {
                sb.append(words[i]);
                sb.append(space.repeat(extra));
            } else {
                while (i < j) {
                    sb.append(words[i]);
                    if (j == l - 1) {
                        sb.append(space);
                    } else {
                        if (extra > 0) {
                            sb.append(space.repeat(q + 1));
                            extra--;
                        } else {
                            sb.append(space.repeat(q));
                        }
                    }
                    i++;
                }
                sb.append(words[j]);
                if (j == l - 1 && maxWidth - sb.length() > 0) {
                    sb.append(space.repeat(maxWidth - sb.length()));
                }
            }
            res.add(sb.toString());
            sb.setLength(0);
            i = j + 1;
            j = i;
            lineL = 0;
            wLen = 0;
        }
        return res;
    }
    public static void main(String[] args) {
        String[] words = new String[]{"What","must","be","acknowledgment","shall","be"};
        int maxWidth = 16;
        List<String> res = fullJustify(words, maxWidth);
        System.out.println(Arrays.toString(res.toArray()));
    }
}
