import java.util.HashMap;
import java.util.Map;

public class LeetCode1 {
    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> sumMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (sumMap.containsKey(target - nums[i])) {
                return new int[]{i, sumMap.get(target - nums[i])};
            }
            sumMap.put(nums[i], i);
        }
        return new int[0];

    }
}
