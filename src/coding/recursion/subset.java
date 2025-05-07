package coding.recursion;

import java.util.ArrayList;
import java.util.List;

public class subset {

    public static void main(String[] args) {

        int[] array = new int[]{1, 2, 3};
        subsets(array);
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        dfs(nums, result, new ArrayList<>(), 0);
        return null;
    }

    public static void dfs(int[] nums, List<List<Integer>> result, List<Integer> current, int index) {


        if (index > nums.length - 1) {
            result.add(new ArrayList<>(current)); //for deeep copy
            return;
        }

        // taken
        current.add(nums[index]);
        dfs(nums, result, current, index + 1);
        current.remove(current.size() - 1);
        //not taken
        dfs(nums, result, current, index + 1);


    }

}
