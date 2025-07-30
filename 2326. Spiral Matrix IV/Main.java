import java.util.*;


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] res = new int[m][n];
        int[][] dir = new int[][]{
                {0,1},{1,0},{0,-1},{-1,0}
        };
        int i, j, ni, nj, curdir = 0;
        for (i = 0; i < m; i++) {
            Arrays.fill(res[i], -1);
        }
        ListNode cur = head;
        i = 0;
        j = 0;
        while (cur != null) {
            res[i][j] = cur.val;
            ni = i + dir[curdir][0];
            nj = j + dir[curdir][1];
            if (ni < 0 || ni == m || nj < 0 || nj == n || res[ni][nj]!= -1) {
                curdir = (curdir + 1) % 4;
                ni = i + dir[curdir][0];
                nj = j + dir[curdir][1];
            }
            i = ni;
            j = nj;
            cur = cur.next;
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int m = 3, n = 5;
        ListNode head = new ListNode(3);
        head.next = new ListNode(0);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(6);
        head.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next = new ListNode(1);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(9);
        head.next.next.next.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(0);
        int[][] res = s.spiralMatrix(m, n, head);
        System.out.println(Arrays.deepToString(res));
    }
}
