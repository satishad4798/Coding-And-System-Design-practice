public class Main {


    public static void main(String[] args) {
        System.out.println("Hello world!");

        int[] arr = new int[]{5};
        int k = 1;
        System.out.println(findMaxAverage(arr, k));
    }

    private static void findMaxAveragef() {
    }


    public static double findMaxAverage(int[] nums, int k) {

        int left = 0;
        int right = 0;
        int left_sol = 0;
        int right_sol = 0;

        double current_sum = 0;
        double max_average = 0;

        for (int i = 0; i < nums.length && right < nums.length; i++) {

            // until first sub-array is created
            if (i < k) {
                current_sum = current_sum + nums[i];

                if (i == k - 1) {
                    max_average = current_sum / k;
                    right = i;
                }
            }
            // finding best sub-array
            else {
                right++;
                current_sum = current_sum - nums[left] + nums[right];
                double current_average = current_sum / k;
                if (max_average < current_average) {
                    max_average = current_average;
                }
                left++;
            }
        }
        return max_average;

    }


}