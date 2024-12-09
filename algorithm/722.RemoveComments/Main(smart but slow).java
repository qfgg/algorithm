import java.util.*;


public class Main {
    public static List<String> removeComments(String[] source) {
        List<String> code = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(source[0]);
        int i, num = source.length, len;
        for (i = 1; i < num; i++) {
            sb.append('\n');
            sb.append(source[i]);
        }
        sb.append('\n');
        String s = sb.toString();
        sb.setLength(0);
        len = s.length();
        char cur, next;
        boolean inLine = false, inBlock = false;
        for (i = 0; i < len - 1; i++) {
            cur = s.charAt(i);
            next = s.charAt(i + 1);
            if (inLine) {
                if (cur == '\n') {
                    inLine = false;
                    if (!sb.isEmpty()) {
                        code.add(sb.toString());
                        sb.setLength(0);
                    }
                }
            } else if (inBlock) {
                if (cur == '*' && next == '/') {
                    i++;
                    inBlock = false;
                }
            } else {
                if (cur == '/' && next == '/') {
                    inLine = true;
                } else if (cur == '/' && next == '*') {
                    i++;
                    inBlock = true;
                } else {
                    if (cur != '\n') {
                        sb.append(cur);
                    } else if (!sb.isEmpty()) {
                        code.add(sb.toString());
                        sb.setLength(0);
                    }
                }
            }
        }
        if (s.charAt(i) == '\n' && !sb.isEmpty()) {
            code.add(sb.toString());
        }
        return code;
    }
    public static void main(String[] args) {
        String[] input1 = new String[]{"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"};
        List<String> output1 = removeComments(input1);
        System.out.println(Arrays.toString(output1.toArray()));
        // ["int main()","{ ","  ","int a, b, c;","a = b + c;","}"]
        String[] input2 = new String[]{"a/*comment", "line", "more_comment*/b"};
        List<String> output2 = removeComments(input2);
        System.out.println(Arrays.toString(output2.toArray()));
        // ["ab"]
        String[] input3 = new String[]{"struct Node{", "    /*/ declare members;/**/", "    int size;", "    /**/int val;", "};"};
        List<String> output3 = removeComments(input3);
        System.out.println(Arrays.toString(output3.toArray()));
        // ["struct Node{","    ","    int size;","    int val;","};"]
        String[] input4 = new String[]{"a/*/b//*c","blank","d/*/e*//f"};
        List<String> output4 = removeComments(input4);
        System.out.println(Arrays.toString(output4.toArray()));
        // ["ae*"]
    }
}
