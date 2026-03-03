import java.util.ArrayList;
import java.util.List;

public class LeetCode46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        calculate(nums, result, new ArrayList<>(), used);
        return result;
    }

    private void calculate(int[] nums, List<List<Integer>> result, List<Integer> path, boolean[] used) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            path.add(nums[i]);
            used[i] = true;
            calculate(nums, result, path, used);
            path.removeLast();
            used[i] = false;
        }
    }
}
