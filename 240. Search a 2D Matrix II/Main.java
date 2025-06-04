import java.util.*;


class Solution {
    boolean searchOne(int[][] matrix, int y, int x1, int x2, int target) {
        int l = x1, r = x2 + 1, m;
        while (l < r) {
            m = (l + r) / 2;
            if (matrix[y][m] == target) {
                return true;
            }
            if (matrix[y][m] < target) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return false;
    }
    boolean findValidRange(int[][] matrix, int m, int n, int y1, int x1, int y2, int x2, int target) {
        if (y1 == y2 && x1 == x2) {
            return matrix[y1][x1] == target;
        }
        int l = y1, r = y2 + 1, mid, maxLessRow = -1, maxLessCol = -1, minLargerRow = m, minLargerCol = n;
        while (l < r) {
            mid = (l + r) / 2;
            if (matrix[mid][x1] <= target) {
                maxLessRow = Math.max(maxLessRow, mid);
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        if (maxLessRow == -1) {
            return false;
        }
        l = x1;
        r = x2 + 1;
        while (l < r) {
            mid = (l + r) / 2;
            if (matrix[y1][mid] <= target) {
                maxLessCol = Math.max(maxLessCol, mid);
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        if (maxLessCol == -1) {
            return false;
        }
        if (maxLessRow < y2 || maxLessCol < x2) {
            return findValidRange(matrix, m, n, y1, x1, maxLessRow, maxLessCol, target);
        }
        l = y1;
        r = y2 + 1;
        while (l < r) {
            mid = (l + r) / 2;
            if (matrix[mid][x2] >= target) {
                minLargerRow = Math.min(minLargerRow, mid);
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (minLargerRow == m) {
            return false;
        }
        l = x1;
        r = x2 + 1;
        while (l < r) {
            mid = (l + r) / 2;
            if (matrix[y2][mid] >= target) {
                minLargerCol = Math.min(minLargerCol, mid);
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (minLargerCol == n) {
            return false;
        }
        if (minLargerRow > y1 || minLargerCol > x1) {
            return findValidRange(matrix, m, n, minLargerRow, minLargerCol, y2, x2, target);
        }
        for (int i = y1; i <= y2; i++) {
            if (searchOne(matrix, y1, x1, x2, target)) {
                return true;
            }
        }
        return false;
    }
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        if (m == 1 && n == 1) {
            return matrix[0][0] == target;
        }
        return findValidRange(matrix, m, n, 0, 0, m - 1, n - 1, target);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] matrix = new int[][]{
                {5,6,9},
                {9,10,11},
                {11,14,18},
        };
        int target = 9;
        boolean res = s.searchMatrix(matrix, target);
        System.out.println(res);
    }
}
