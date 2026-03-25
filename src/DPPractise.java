import java.util.*;

public class DPPractise {

    private static int maximumValue(int[] weights, int[] values, int W) {

        int[] dp = new int[W + 1];
        int n = values.length;
        for (int i = 0; i < n; i++) {
            for (int w = W; w >= weights[i]; w--) {
                dp[w] = Math.max(dp[w], values[i] + dp[w - weights[i]]);
            }
        }
        return dp[W];
    }

    private static boolean subsetSum(int[] values, int target) {
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int value : values) {
            for (int j = target; j >= value; j--) {
                dp[j] = (dp[j - value] || dp[j]);
            }
        }
        return dp[target];
    }

    private static boolean PartitionSubsetSum(int[] values) {
        int target = Arrays.stream(values).sum();
        if (target % 2 != 0) return false;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int value : values) {
            for (int j = target; j >= value; j--) {
                dp[j] = (dp[j - value] || dp[j]);
            }
        }
        return dp[target];
    }

    private static boolean coins(int[] coins, int amount) {
        boolean[] dp = new boolean[amount + 1];
        dp[0] = true;
        for (int value : coins) {
            for (int j = value; j <= amount; j++) {
                dp[j] = dp[j] || dp[j - value];
            }
        }
        return dp[amount];
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int original = image[sr][sc];
        if (original == color) return image;

        for (int row = 0; row < image.length; row++) {
            for (int col = 0; col < image[0].length; col++) {
                if (image[row][col] == original) updateColor(image, row, col, color, original);
            }
        }
        return image;
    }

    private void updateColor(int[][] image, int row, int col, int color, int original) {
        if (row < 0 || col < 0 || row >= image.length || col >= image[0].length || image[row][col] != original) {
            return;
        }

        image[row][col] = color;
        updateColor(image, row + 1, col, color, original);
        updateColor(image, row - 1, col, color, original);
        updateColor(image, row, col + 1, color, original);
        updateColor(image, row, col - 1, color, original);
    }

    public boolean exist(char[][] board, String word) {
        if (word.isEmpty()) return true;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == word.charAt(0) && verify(board, 0, row, col, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean verify(char[][] board, int idx, int row, int col, String word) {
        if (idx == word.length()) return true;
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] != word.charAt(idx)) {
            return false;
        }
        char temp = board[row][col];
        board[row][col] = '#';
        boolean found = verify(board, idx + 1, row + 1, col, word) || verify(board, idx + 1, row - 1, col, word) || verify(board, idx + 1, row, col + 1, word) || verify(board, idx + 1, row, col - 1, word);

        board[row][col] = temp;
        return found;
    }

    static class FlightEdge {
        int node, cost, stops;

        FlightEdge(int n, int c, int s) {
            node = n;
            cost = c;
            stops = s;
        }
    }


    public int findCheapestPriceOP(int n, int[][] flights, int src, int dst, int k) {

        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int[] f : flights) {
            graph[f[0]].add(new int[]{f[1], f[2]});
        }

        PriorityQueue<FlightEdge> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));

        int[][] dist = new int[n][k + 2];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);

        pq.add(new FlightEdge(src, 0, 0));
        dist[src][0] = 0;

        while (!pq.isEmpty()) {
            FlightEdge curr = pq.poll();

            int node = curr.node;
            int cost = curr.cost;
            int stops = curr.stops;

            if (node == dst) return cost;

            if (stops > k) continue;

            for (int[] nei : graph[node]) {
                int next = nei[0];
                int newCost = cost + nei[1];

                if (newCost < dist[next][stops + 1]) {
                    dist[next][stops + 1] = newCost;
                    pq.add(new FlightEdge(next, newCost, stops + 1));
                }
            }
        }

        return -1;
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        List<int[]>[] adjacent = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adjacent[i] = new ArrayList<>();
        }

        for (int[] flight : flights) {
            adjacent[flight[0]].add(new int[]{flight[1], flight[2]});
        }

        PriorityQueue<FlightEdge> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        queue.offer(new FlightEdge(src, 0, 0));

        while (!queue.isEmpty()) {
            FlightEdge edge = queue.poll();
            if (edge.node == dst) {
                return edge.cost;
            }
            if (edge.stops > k) continue;

            for (int[] nei : adjacent[edge.node]) {
                queue.add(new FlightEdge(nei[0], edge.cost + nei[1], edge.stops + 1));
            }
        }

        return -1;
    }

    class State {
        int to;
        int weight;
    }

    public int[] shortestDistance(int n, int[][] edges) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        for (int v = 0; v < n; v++) {

            for (int[] e : edges) {
                if (dist[e[0]] != Integer.MAX_VALUE && dist[e[0]] + e[2] < dist[e[1]]) {
                    if (v == n - 1) return new int[]{-1};
                    dist[e[1]] = dist[e[0]] + e[2];
                }
            }
        }
        return dist;
    }
}
