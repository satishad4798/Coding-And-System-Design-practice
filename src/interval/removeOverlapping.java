package interval;

import java.util.Arrays;

public class removeOverlapping {
    public static void main(String[] args) {

        int[][] intervals = {{-3035, 30075}, {1937, 6906}, {11834, 20971}, {44578, 45600}, {28565, 37578}, {19621, 34415}, {32985, 36313}, {-8144, 1080}, {-15279, 21851}, {-27140, -14703}, {-12098, 16264}, {-36057, -16287}, {47939, 48626}, {-15129, -5773}, {10508, 46685}, {-35323, -26257}};

        extracted(intervals);

    }

    private static int extracted(int[][] intervals) {

        // sort intervals based on index row 0
        // Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        System.out.println();
        int size = intervals.length;
        int i = 1;
        int leftPointer = 0;
        int toRemove = 0;
        while (i < size) {
            // non overlapping
            if (intervals[leftPointer][1] <= intervals[i][0]) {
                leftPointer = i;
            }
            // overlapping
            else {
                if (intervals[leftPointer][1] < intervals[i][1])
                    leftPointer = leftPointer;
                else
                    leftPointer = i;
                toRemove++;
            }
            i++;
        }

        System.out.println(toRemove);
        return toRemove;
    }

}






