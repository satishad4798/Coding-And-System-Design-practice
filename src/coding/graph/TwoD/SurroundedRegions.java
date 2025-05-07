package coding.graph.TwoD;

public class SurroundedRegions {

    public static void main(String[] args) {

        char[][] board = {{'x', 'x', 'x', 'x'}, {'x', 'o', 'o', 'x'}, {'x', 'x', 'o', 'x'}, {'x', 'o', 'x', 'x'}};
        solve(board);
        System.out.println();
    }

    public static void solve(char[][] board) {

        int rows = board.length;
        int columns = board[0].length;
        boolean[][] visited = new boolean[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                if (board[i][j] == 'o' && !visited[i][j]) {
                    traverseSurrounding(i, j, rows, columns, board, visited);
                }

            }

        }


    }

    private static boolean traverseSurrounding(int i, int j, int rows, int columns, char[][] board, boolean[][] visited) {
        visited[i][j] = true;

        boolean result = isSurrounded(i, j + 1, rows, columns, board, visited) &&
                isSurrounded(i, j - 1, rows, columns, board, visited) &&
                isSurrounded(i + 1, j, rows, columns, board, visited) &&
                isSurrounded(i - 1, j, rows, columns, board, visited);

        if (result) {
            board[i][j] = 'x';
        }

        return result;

    }

    private static boolean isSurrounded(int i, int j, int rows, int columns, char[][] board, boolean[][] visited) {


        if (i < 0 || j < 0 || i >= rows || j >= columns) {
            return false;
        }
        if (board[i][j] == 'o' && !visited[i][j]) {
            return traverseSurrounding(i, j, rows, columns, board, visited);
        } else {
            return true;
        }
    }

}
