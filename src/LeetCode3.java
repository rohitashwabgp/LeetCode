import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LeetCode3 {


    Map<Character, Integer> indexMap = new ConcurrentHashMap<>();
    int left = 0;
    int[] indexArray;

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);
            if (indexMap.containsKey(current)) {
                left = Math.max(left, indexMap.get(current) + 1);
            }
            indexMap.put(current, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public int lengthOfLongestSubstringOptimised(String s) {
        int maxLength = 0;
        indexArray = new int[128];
        Arrays.fill(indexArray, -1);
        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);

            left = Math.max(left, indexArray[current] + 1);

            indexArray[current] = right;
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}
