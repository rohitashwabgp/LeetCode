import java.sql.Array;
import java.util.*;

public class LeetCode347 {
    public static int[] topKFrequentMap(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((entry1, entry2) ->
                entry2.getValue() - entry1.getValue());
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            queue.offer(entry);
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            Map.Entry<Integer, Integer> entry = queue.poll();
            if (entry != null)
                result[i] = entry.getKey();
        }
        return result;
    }

    public static int[] topKFrequent(int[] nums, int k) {
        List<Integer>[] bucket = new ArrayList[nums.length + 1];
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        for (int key : freqMap.keySet()) {
            int freq = freqMap.get(key);
            if (bucket[freq] == null)
                bucket[freq] = new ArrayList<>();
            bucket[freq].add(key);
        }

        int[] result = new int[k];
        int index = 0;
        for (int i = bucket.length - 1; i >= 0 && index < k; i--) {
            if (bucket[i] != null) {
                for (int num : bucket[i]) {
                    result[index++] = num;
                    if (index == k) break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] input = new int[]{1, 1, 1, 2, 2, 3};
        int[] result = topKFrequent(input, 2);
    }
}


//static PriorityQueue<Integer> storeTopK = new PriorityQueue<>();
//int[] result = new int[k];
//        for (int num : nums) {
//        if (storeTopK.size() < k) {
//        storeTopK.offer(num);
//        } else if (storeTopK.peek() != null && num > storeTopK.peek()) {
//        storeTopK.poll();
//            storeTopK.offer(num);
//        }
//                }
//                for (int i = 0; i < k; i++) {
//        if (!storeTopK.isEmpty())
//result[i] = storeTopK.poll();
//    }
//            return result;