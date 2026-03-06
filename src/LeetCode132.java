import java.util.Arrays;

public class LeetCode132 {

    public int minCut(String s) {
        boolean[][] palindrome = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            palindrome[i][i] = true;
        }

        for (int len = 2; len <= s.length(); len++) {
            for (int i = 0; i <= s.length() - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (len == 2 || palindrome[i + 1][j - 1]) {
                        palindrome[i][j] = true;
                    }
                }
            }
        }

        int n = s.length();
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            if (palindrome[0][i]) {
                dp[i] = 0;
                continue;
            }

            dp[i] = i;

            for (int j = 1; j <= i; j++) {
                if (palindrome[j][i]) {
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }

        return dp[n - 1];
    }
}
