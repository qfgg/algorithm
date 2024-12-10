import java.util.*;


// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;


    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}

public class Main {
    public static Node dfs(int[][] grid, int r, int c, int n) {
        Node root = new Node();
        if (n == 1) {
            root.isLeaf = true;
            root.val = grid[r][c] == 1;
            return root;
        }
        Node tl, tr, bl, br;
        tl = dfs(grid, r - n / 2, c - n / 2, n / 2);
        tr = dfs(grid, r - n / 2, c, n /2);
        bl = dfs(grid, r, c - n / 2, n /2);
        br = dfs(grid, r, c, n /2);
        if ((tl.isLeaf && tl.val) && (tr.isLeaf && tr.val) &&
                (bl.isLeaf && bl.val) && (br.isLeaf && br.val)) {
            root.val = true;
            root.isLeaf = true;
        } else if ((tl.isLeaf && !tl.val) && (tr.isLeaf && !tr.val) &&
                (bl.isLeaf && !bl.val) && (br.isLeaf && !br.val)) {
            root.val = false;
            root.isLeaf = true;
        } else {
            root.val = true;
            root.isLeaf = false;
            root.topLeft = tl;
            root.topRight = tr;
            root.bottomLeft = bl;
            root.bottomRight = br;
        }
        return root;
    }
    public static Node construct(int[][] grid) {
        return dfs(grid, grid.length - 1, grid.length - 1, grid.length);
    }
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0}
        };
        Node res = construct(grid);
        System.out.println(res);
    }
}
