import java.util.*;


public class Main {
    public static List<String> removeComments(String[] source) {
        List<String> code = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int i, end, blockEnd = 0;
        char cur, next;
        boolean inBlock = false;
        for (String s : source) {
            end = s.length() - 1;
            for (i = 0; i < end; i++) {
                cur = s.charAt(i);
                next = s.charAt(i + 1);
                if (inBlock) {
                    if (cur == '*' && next == '/') {
                        if (i < end - 1) {
                            blockEnd = i + 2;
                            // 1 char left after */
                            if (blockEnd == end) {
                                if (sb.isEmpty()) {
                                    code.add(s.substring(end));
                                } else {
                                    sb.append(s.substring(end));
                                    code.add(sb.toString());
                                    sb.setLength(0);
                                }
                                blockEnd = 0;
                            }
                        } else if (!sb.isEmpty()) {
                            code.add(sb.toString());
                            sb.setLength(0);
                        }
                        inBlock = false;
                        i++;
                    }
                } else {
                    if (cur == '/' && next == '/') {
                        if (i > blockEnd) {
                            if (sb.isEmpty()) {
                                code.add(s.substring(blockEnd, i));
                            } else {
                                sb.append(s.substring(blockEnd, i));
                                code.add(sb.toString());
                                sb.setLength(0);
                            }
                        } else if (i == blockEnd) {
                            if (!sb.isEmpty()) {
                                code.add(sb.toString());
                                sb.setLength(0);
                            }
                        }
                        blockEnd = 0;
                        break;
                    } else if (cur == '/' && next == '*') {
                        if (i > blockEnd) {
                            sb.append(s.substring(blockEnd, i));
                        }
                        blockEnd = 0;
                        inBlock = true;
                        i++;
                    } else if (i == end - 1) {
                        if (blockEnd > 0) {
                            if (sb.isEmpty()) {
                                code.add(s.substring(blockEnd));
                            } else {
                                sb.append(s.substring(blockEnd));
                                code.add(sb.toString());
                                sb.setLength(0);
                            }
                            blockEnd = 0;
                        } else {
                            code.add(s);
                        }
                    }
                }
            }
            // this line has only 1 char, so no change
            if (!inBlock && end == 0) {
                code.add(s);
            }
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
        // ["ableavethis"]
        String[] input3 = new String[]{"struct Node{", "    /*/ declare members;/**/", "    int size;", "    /**/int val;", "};"};
        List<String> output3 = removeComments(input3);
        System.out.println(Arrays.toString(output3.toArray()));
        // ["struct Node{","    ","    int size;","    int val;","};"]
        String[] input4 = new String[]{"a/*/b//*c","blank","d/*/e*//f"};
        List<String> output4 = removeComments(input4);
        System.out.println(Arrays.toString(output4.toArray()));
        // ["ae*"]
        String[] input5 = new String[]{"a","/*comment", "line", "more_comment*/b"};
        List<String> output5 = removeComments(input5);
        System.out.println(Arrays.toString(output5.toArray()));
    }
}
