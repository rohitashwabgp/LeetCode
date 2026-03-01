import java.util.*;
import java.util.stream.Collectors;

public class LeetCode310 {

    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return List.of(0);

        List<Set<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new HashSet<>());

        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        Queue<Integer> leaves = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (graph.get(i).size() == 1) leaves.add(i);
        }

        int remaining = n;
        while (remaining > 2) {
            int size = leaves.size();
            remaining -= size;

            for (int i = 0; i < size; i++) {
                int leaf = leaves.poll();
                int neighbor = graph.get(leaf).iterator().next();
                graph.get(neighbor).remove(leaf);
                if (graph.get(neighbor).size() == 1) leaves.add(neighbor);
            }
        }

        return new ArrayList<>(leaves);
    }

    public static void main(String[] args) {
        int[][] edges = {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
        int n = 6;

        List<Integer> out = findMinHeightTrees(n, edges);
    }
}
