public class LeetCode5 {
    public String longestPalindrome(String s) {
        boolean[][] palindrome = new boolean[s.length()][s.length()];
        int startIndex = -1;
        int maxLength = -1;
        for (int i = 0; i < s.length(); i++) {
            palindrome[i][i] = true;
        }

        for (int len = 2; len <= s.length(); len++) {
            for (int i = 0; i + len - 1 < s.length(); i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (len == 2 || palindrome[i + 1][j - 1]) {
                        palindrome[i][j] = true;
                        if (len > maxLength) {
                            maxLength = len;
                            startIndex = i;
                        }

                    }
                }
            }
        }
        return s.substring(startIndex, startIndex + maxLength);
    }

    public String longestPalindromeOptimised(String s) {
        if (s == null || s.length() < 2) return s;
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expand(s, i, i);       // odd
            int len2 = expand(s, i, i + 1);   // even
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }


}
