package interval;

import java.util.ArrayList;
import java.util.List;

public class insertInterval {
    public static void main(String[] args) {


        extracted();

    }

    private static int[][] extracted() {
        //  int[][] intervals = {{1, 3}, {4, 6}, {7, 9}, {10, 12}, {13, 15}};
        int[][] intervals = {};

        int[] newInterval = {2, 8};
        List<int[]> mergedInterval = new ArrayList<>();

        //copy all starting non-overlapping interval
        int i = 0;
        int length = intervals.length;

        while (i < length && intervals[i][1] < newInterval[0]) {
            mergedInterval.add(intervals[i]);
            i++;
        }
        //   <    3-6    <
        int nextPointer = i;
        //merge all overlapping intervals and insert
        while (i < length && intervals[i][0] < newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        mergedInterval.add(newInterval);

        //copy rest of the array
        while (i < length) {
            mergedInterval.add(intervals[i++]);
        }
        List<int[]> mergedInterval3 = new ArrayList<>();
        return mergedInterval.toArray(new int[mergedInterval.size()][]);

    }
}






