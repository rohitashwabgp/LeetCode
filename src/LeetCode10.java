public class LeetCode10 {
    public boolean isMatch(String s, String p) {
        return match(s, p, p.length() - 1, s.length() - 1);
    }

    private boolean match(String given, String prefix, int pIndex, int gIndex) {
        if (pIndex < 0 && gIndex < 0) {
            return true;
        }
        if (pIndex < 0) {
            return false;
        }
        if (gIndex < 0) {
            if (prefix.charAt(pIndex) == '*') {
                return match(given, prefix, pIndex - 2, gIndex);
            }
            return false;
        }

        if (prefix.charAt(pIndex) == '.' || prefix.charAt(pIndex) == given.charAt(gIndex)) {
            return match(given, prefix, pIndex - 1, gIndex - 1);
        } else if (prefix.charAt(pIndex) == '*') {
            char prev = prefix.charAt(pIndex - 1);
            return match(given, prefix, pIndex - 2, gIndex) ||
                    ((prev == '.' || prev == given.charAt(gIndex)) && match(given, prefix, pIndex, gIndex - 1));
        }
        return false;
    }
}
