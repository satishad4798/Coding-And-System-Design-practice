package coding.graph.TwoD;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class floodFill {
    static Set<String> visited = new HashSet<>();
    static int columns;
    static int previousColour;
    private static int rows;

    public static void main(String[] args) {

        int[][] image = new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        int[][] image2 = new int[][]{{0, 0, 0}, {0, 0, 0}};

        //  floodFill(image, 1, 1, 2);
        int[][] d = floodFill(image2, 1, 0, 2);
        System.out.println();

    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sr, sc});
        visited.add(sr + ":" + sc);

        previousColour = image[sr][sc];
        rows = image.length;
        columns = image[0].length;

        while (!q.isEmpty()) {
            int[] current_values = q.poll();
            int i = current_values[0];
            int j = current_values[1];

            System.out.println(q);

            System.out.println(i + ":" + j + "  row:" + rows + "  column:" + columns);
            image[i][j] = color;

            checkAndAdd(i, j + 1, image, q);
            checkAndAdd(i + 1, j, image, q);
            checkAndAdd(i, j - 1, image, q);
            checkAndAdd(i - 1, j, image, q);


        }


        return image;


    }

    public static void checkAndAdd(int i, int j, int[][] image, Queue q) {

        if (visited.contains(i + ":" + j)) {
            return;

        }
        if (0 <= i && i < rows && 0 <= j && j < columns && (image[i][j] == previousColour)) {
            q.add(new int[]{i, j});
            visited.add(i + ":" + j);
            System.out.println();
        } else {
            System.out.println();
        }
    }


}
