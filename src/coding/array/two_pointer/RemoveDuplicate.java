package coding.array.two_pointer;

import java.util.Arrays;

public class RemoveDuplicate {


    public static void main(String[] args) {


        int[] array = new int[]{1, 1, 2, 3, 4, 5, 5, 6, 7, 7, 8, 8, 9, 10};

        int left = 0;
        int right = 0;


        while (right < array.length) {

            if (array[left] == array[right]) {
                right++;
            } else {
                left++;
                array[left] = array[right];
                right++;
            }

        }
        while (left < array.length) {
            array[left] = 0;
            left++;
        }

        Arrays.stream(array).asLongStream().forEach(value -> System.out.println(value));


    }
}
