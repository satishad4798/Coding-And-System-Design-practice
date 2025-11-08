package coding.graph.TwoD;

public class NumIslands {


    public static void main(String[] args) {

        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        System.out.println("no.of islands:" + numIslands(grid));

    }


    public static int numIslands(char[][] grid) {

        int row = grid.length;
        int column = grid[0].length;
        boolean[][] visited = new boolean[row][column];
        int noOfIslands = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    traverseNodes(i, j, row, column, grid, visited);
                    noOfIslands++;
                }
            }
        }
        return noOfIslands;
    }

    private static void traverseNodes(int i, int j, int row, int column, char[][] grid, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= row || j >= column || grid[i][j] == '0' || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        traverseNodes(i, j + 1, row, column, grid, visited);
        traverseNodes(i, j - 1, row, column, grid, visited);
        traverseNodes(i + 1, j, row, column, grid, visited);
        traverseNodes(i - 1, j, row, column, grid, visited);
    }
}
