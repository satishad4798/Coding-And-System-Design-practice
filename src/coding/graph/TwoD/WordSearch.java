package coding.graph.TwoD;

public class WordSearch {

    public static void main(String[] args) {
        char[][] board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};

        String word = "ABCCED";
        boolean result;
        result = exist(board, word);

        board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        word = "SEE";
        result = exist(board, "SEE");

        System.out.println(result);
    }

    public static boolean exist(char[][] board, String word) {
        int row = board.length;
        int columns = board[0].length;
        boolean result = false;

        outerLoop:
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean[][] visited = new boolean[row][columns];
                    result = dfs(board, row, columns, visited, i, j, 0, word);
                    if (result) {
                        break outerLoop;
                    }
                }
            }

        }

        return result;

    }

    private static boolean dfs(char[][] board, int row, int columns, boolean[][] visited, int i, int j, int characterIndex, String word) {

        if (i < 0 || j < 0 || i >= row || j >= columns || visited[i][j]) {
            return false;
        }

        if (characterIndex == word.length() - 1 && board[i][j] == word.charAt(characterIndex)) {
            return true;
        }
        if (board[i][j] == word.charAt(characterIndex)) {
            visited[i][j] = true;
            boolean result = dfs(board, row, columns, visited, i + 1, j, characterIndex + 1, word) ||
                    dfs(board, row, columns, visited, i - 1, j, characterIndex + 1, word) ||
                    dfs(board, row, columns, visited, i, j + 1, characterIndex + 1, word) ||
                    dfs(board, row, columns, visited, i, j - 1, characterIndex + 1, word);
            return result;

        } else {
            return false;
        }
    }

}
