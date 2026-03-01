import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LeetCode56 {


    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int[] current = intervals[0];
        List<int[]> intervalsUpdated = new ArrayList<>();
        intervalsUpdated.add(current);
        for (int i = 1; i < intervals.length; i++) {
            int[] next = intervals[i];
            if (next[0] <= current[1]) {
                current[1] = Math.max(current[1], next[1]);
            } else {
                current = next;
                intervalsUpdated.add(current);
            }
        }
        return intervalsUpdated.toArray(new int[intervalsUpdated.size()][]);
    }

    public int[][] mergeOptimised(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int idx = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= intervals[idx][1]) {
                intervals[idx][1] = Math.max(intervals[idx][1], intervals[i][1]);
            } else {
                idx++;
                intervals[idx] = intervals[i];
            }
        }
        return Arrays.copyOf(intervals, idx + 1);
    }
}
