package coding.binarySearch;

class binarySearch {

    public static void main(String[] args) {

        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
        search(nums, 9);

    }

    public static int search(int[] nums, int target) {


        int size = nums.length - 1;
        if (size == 0)
            return -1;

        return binarySearch(nums, target, 0, size / 2, size);

    }

    static int binarySearch(int[] nums, int targer, int left, int mid, int right) {

        if (nums[mid] == targer) {
            return mid;
        }
        if (left == right || mid == right) {
            return -1;
        }

        if (nums[mid] > targer) {
            return binarySearch(nums, targer, left, mid / 2, mid);
        } else {
            return binarySearch(nums, targer, mid, (mid + right) / 2, right);
        }

    }
}