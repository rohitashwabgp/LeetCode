import java.util.*;

public class LeetCode3666 {
    public int minOperations(String s, int k) {
        int n = s.length();
        int m = 0;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        List<TreeSet<Integer>> nodeSets = new ArrayList<>();
        nodeSets.add(new TreeSet<>());
        nodeSets.add(new TreeSet<>());
        for (int i = 0; i <= n; i++) {
            nodeSets.get(i % 2).add(i);
            if (i < n && s.charAt(i) == '0') {
                m++;
            }
        }
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(m);
        dist[m] = 0;
        nodeSets.get(m % 2).remove(m);
        while (!q.isEmpty()) {
            m = q.poll();
            int c1 = Math.max(k - n + m, 0);
            int c2 = Math.min(m, k);
            int lnode = m + k - 2 * c2;
            int rnode = m + k - 2 * c1;
            TreeSet<Integer> nodeSet = nodeSets.get(lnode % 2);
            for (Integer m2 = nodeSet.ceiling(lnode); m2 != null && m2 <= rnode; m2 = nodeSet.ceiling(lnode)) {
                dist[m2] = dist[m] + 1;
                q.offer(m2);
                nodeSet.remove(m2);
            }
        }
        return dist[0] == Integer.MAX_VALUE ? -1 : dist[0];

    }

    public int minOperationsN(String s, int k) {
        long n = s.length(), zero = 0, one = 0;
        for (char ch : s.toCharArray()) if (ch == '0') zero++;
        one = n - zero;
        if (zero == 0) return 0;

        for (long i = 1; i <= n; i++) {
            long flip = i * (long) k;
            if ((flip - zero) % 2 != 0) continue;
            if (i % 2 == 1) {
                if (flip >= zero && flip <= (zero * i + one * (i - 1))) return (int) i;
            } else if (flip >= zero && flip <= (zero * (i - 1) + one * i)) return (int) i;
        }

        return -1;
    }

}
