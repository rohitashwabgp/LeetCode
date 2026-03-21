import java.util.HashSet;
import java.util.Set;

public class LeetCode1044 {

    public String longestDupSubstring(String s) {
        int n = s.length();
        int left = 1, right = n - 1;
        String ans = "";

        while (left <= right) {
            int mid = left + (right - left) / 2;
            String res = search(s, mid);

            if (res != null) {
                ans = res;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    private String search(String s, int len) {
        int n = s.length();
        long mod1 = 1_000_000_007;
        long mod2 = 1_000_000_009;
        long base = 256;

        long hash1 = 0, hash2 = 0;
        long power1 = 1, power2 = 1;

        // base^(len-1)
        for (int i = 0; i < len - 1; i++) {
            power1 = (power1 * base) % mod1;
            power2 = (power2 * base) % mod2;
        }

        // initial hash
        for (int i = 0; i < len; i++) {
            hash1 = (hash1 * base + s.charAt(i)) % mod1;
            hash2 = (hash2 * base + s.charAt(i)) % mod2;
        }

        Set<Long> seen = new HashSet<>();
        seen.add(hash1 * mod2 + hash2); // combine hashes

        for (int i = len; i < n; i++) {
            // remove left char
            hash1 = (hash1 - s.charAt(i - len) * power1 % mod1 + mod1) % mod1;
            hash2 = (hash2 - s.charAt(i - len) * power2 % mod2 + mod2) % mod2;

            // shift + add new char
            hash1 = (hash1 * base + s.charAt(i)) % mod1;
            hash2 = (hash2 * base + s.charAt(i)) % mod2;

            long combined = hash1 * mod2 + hash2;

            if (seen.contains(combined)) {
                return s.substring(i - len + 1, i + 1);
            }
            seen.add(combined);
        }

        return null;
    }
}
