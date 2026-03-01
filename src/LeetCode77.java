import java.util.ArrayList;
import java.util.List;

public class LeetCode77 {

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> comb = new ArrayList<>();
        combination(n, k, comb, new ArrayList<>(), 1);
        return comb;
    }

    private static void combination(int n, int k, List<List<Integer>> comb, List<Integer> path, int start) {
        if (k == 0) {
            comb.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i <= n; i++) {
            path.add(i);
            combination(n, k - 1, comb, path, i + 1);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> comb = combine(4, 2);
        comb.forEach(System.out::println);
    }
}
