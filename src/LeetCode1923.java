import java.util.HashSet;
import java.util.Set;

public class LeetCode1923 {

    public int longestCommonSubpath(int n, int[][] paths) {
        int minLen = Integer.MAX_VALUE;
        for (int[] path : paths) {
            minLen = Math.min(minLen, path.length);
        }

        int left = 0, right = minLen;
        int ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (hasCommonSubpath(paths, mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    private boolean hasCommonSubpath(int[][] paths, int len) {
        if (len == 0) return true;

        long base = 1000003;
        long mod1 = 1_000_000_007;
        long mod2 = 1_000_000_009;

        Set<String> common = null;

        for (int[] path : paths) {
            if (path.length < len) return false;

            Set<String> current = new HashSet<>();

            long hash1 = 0, hash2 = 0;
            long power1 = 1, power2 = 1;

            for (int i = 0; i < len; i++) {
                hash1 = (hash1 * base + path[i]) % mod1;
                hash2 = (hash2 * base + path[i]) % mod2;

                if (i < len - 1) {
                    power1 = (power1 * base) % mod1;
                    power2 = (power2 * base) % mod2;
                }
            }

            current.add(hash1 + "#" + hash2);

            for (int i = len; i < path.length; i++) {
                hash1 = (hash1 - path[i - len] * power1 % mod1 + mod1) % mod1;
                hash1 = (hash1 * base + path[i]) % mod1;

                hash2 = (hash2 - path[i - len] * power2 % mod2 + mod2) % mod2;
                hash2 = (hash2 * base + path[i]) % mod2;

                current.add(hash1 + "#" + hash2);
            }

            if (common == null) {
                common = current;
            } else {
                common.retainAll(current);
                if (common.isEmpty()) return false;
            }
        }

        return common != null && !common.isEmpty();
    }
}
