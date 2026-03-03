import java.util.HashMap;
import java.util.Map;

public class LeetCode87 {
    Map<String, Boolean> memo = new HashMap<>();
    public boolean isScramble(String s1, String s2) {

        String key = s1.concat("#").concat(s2);
        if (s1.equals(s2)) {
            memo.put(key, true);
            return true;
        }
        if (s1.length() != s2.length()) {
            memo.put(key, false);
            return false;
        }
        int[] freq = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            freq[s1.charAt(i) - 'a']++;
            freq[s2.charAt(i) - 'a']--;
        }
        for (int el : freq) {
            if (el != 0) {
                return false;
            }
        }
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        for (int i = 1; i < s1.length(); i++) {
            //no swap
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                memo.put(key, true);
                return true;
            }

            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) {
                memo.put(key, true);
                return true;
            }
        }
        memo.put(key, false);
        return false;
    }
}
