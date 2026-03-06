import java.util.Arrays;

public class LeetCode115 {
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()][t.length()];
        for (int[] each : dp)
            Arrays.fill(each, -1);
        return numDistinct1(s, t, 0, 0, dp);
    }

    public int numDistinct1(String s, String t, int sIndex, int tIndex, int[][] dp) {
        if (dp[sIndex][tIndex] != -1) return dp[sIndex][tIndex];
        if (tIndex >= t.length())
            return 1;
        if (sIndex >= s.length())
            return 0;
        int count = 0;
        if (s.charAt(sIndex) == t.charAt(tIndex)) {
            count = count + numDistinct1(s, t, sIndex + 1, tIndex + 1, dp);
        }
        count = count + numDistinct1(s, t, sIndex + 1, tIndex, dp);

        return dp[sIndex][tIndex] = count;
    }
}
