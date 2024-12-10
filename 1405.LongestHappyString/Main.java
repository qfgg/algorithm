// 1. find the largest count, for example, a is biggest so "a" is most
// 2. the number of slots is slotNum = (a + 1) / 2, which is the minimum
// number of other chars to split all "a"s
// 3. bDoubleSlotNum = b > slotNum ? b - slotNum : 0,
// bSlotNum = bDoubleSlotNum > 0 ? slotNum : b
// cDoubleSlotNum = c > slotNum ? c - slotNum : 0,
// cSlotNum = cDoubleSlotNum > 0 ? slotNum : c
// 4. if bSlotNum + cSlotNum <= slotNum, first inset "bb"s (if any) and
// finally "b" (if any) to insert, and then inset "cc"s (if any) and
// finally "c" (if any)
// 5. if bSlotNum + cSlotNum > slotNum, twiceSlotNum = bSlotNum + cSlotNum
// - slotNum, which means how many slots need to insert both "b" and "c" at
// the same time, like: "bbcc", "bbc", "bcc", "bc"
// (depends on how many "b"s and "c"s)
import java.util.*;


class Main {
    public static String makeStr(int[] count, int maxIdx, int idx2, int idx3, int slotNum2, int slotNum3, int doubleSlotNum2, int doubleSlotNum3, int twiceSlotNum) {
        String[][] str = new String[][]{{"a", "b", "c"}, {"aa", "bb", "cc"}};
        StringBuilder ret = new StringBuilder("");
        while (count[idx2] > 0 || count[idx3] > 0) {
            if (count[maxIdx] >= 2) {
                ret.append(str[1][maxIdx]);
                count[maxIdx] -= 2;
            } else if (count[maxIdx] == 1) {
                ret.append(str[0][maxIdx]);
                count[maxIdx]--;
            }
            if (slotNum2 > 0) {
                if (doubleSlotNum2 > 0) {
                    ret.append(str[1][idx2]);
                    count[idx2] -= 2;
                    doubleSlotNum2--;
                } else {
                    ret.append(str[0][idx2]);
                    count[idx2]--;
                }
                slotNum2--;
            } else if (slotNum3 > 0) {
                if (doubleSlotNum3 > 0) {
                    ret.append(str[1][idx3]);
                    count[idx3] -= 2;
                    doubleSlotNum3--;
                } else {
                    ret.append(str[0][idx3]);
                    count[idx3]--;
                }
            }
            if (twiceSlotNum > 0) {
                if (doubleSlotNum3 > 0) {
                    ret.append(str[1][idx3]);
                    count[idx3] -= 2;
                    doubleSlotNum3--;
                } else {
                    ret.append(str[0][idx3]);
                    count[idx3]--;
                }
                twiceSlotNum--;
            }
        }
        if (count[maxIdx] >= 2) {
            ret.append(str[1][maxIdx]);
        } else if (count[maxIdx] == 1) {
            ret.append(str[0][maxIdx]);
        }
        return ret.toString();
    }
    public static String longestDiverseString(int a, int b, int c) {
        int[] count = new int[]{a, b, c};
        int maxIdx = 2;
        int idx2 = 0;
        int idx3 = 1;
        if (a >= b) {
            if (a >= c) {
                maxIdx = 0;
                idx2 = 2;
            }
        } else {
            if (b >= c) {
                maxIdx = 1;
                idx3 = 2;
            }
        }
        int slotNum = (count[maxIdx] + 1) / 2;
        int doubleSlotNum2 = count[idx2] > slotNum ? count[idx2] - slotNum : 0;
        int slotNum2 = doubleSlotNum2 > 0 ? slotNum : count[idx2];
        int doubleSlotNum3 = count[idx3] > slotNum ? count[idx3] - slotNum : 0;
        int slotNum3 = doubleSlotNum3 > 0 ? slotNum : count[idx3];
        int twiceSlotNum = slotNum2 + slotNum3 - slotNum;
        return makeStr(count, maxIdx, idx2, idx3, slotNum2, slotNum3, doubleSlotNum2, doubleSlotNum3, twiceSlotNum);
    }
    public static void main(String[] args) {
        System.out.println(longestDiverseString(1,1,7));
    }
}
