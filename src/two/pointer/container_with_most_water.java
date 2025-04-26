package two.pointer;

public class container_with_most_water {

    public static void main(String[] args) {

        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        //bruteForce(height);
        int result = optimized(height);

    }

    private static int optimized(int[] height) {
        int max_volume = 0;
        int size = height.length;
        int left = 0;
        int right = size - 1;
        int i = 0;
        while (left < right) {
            int current_volume = Math.min(height[i], height[right]);

            if (current_volume > max_volume) {
                max_volume = current_volume;
                left = i;

            }
            i++;


        }

        return 0;
    }

    static int bruteForce(int[] height) {

        int result = 0;
        int size = height.length;

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int container_max_height = Math.min(height[i], height[j]);
                result = Math.max(result, container_max_height * (j - i));
                System.out.println(height[i] + ":__:" + height[j] + "->" + (j - i) + ":->" + (container_max_height * j - i));
            }
        }


        return result;
    }


}
