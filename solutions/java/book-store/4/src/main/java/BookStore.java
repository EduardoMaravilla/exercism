import java.util.*;
import java.util.stream.Collectors;

public class BookStore {
    private static final double BOOK_PRICE = 8.00;
    private static final double[] DISCOUNTS = {0.00, 0.05, 0.10, 0.20, 0.25};

    private final Map<List<Integer>, Double> memo = new HashMap<>();

    public double calculateBasketCost(List<Integer> books) {
        if (books.isEmpty()) return 0.0;
        Map<Integer, Long> countsMap = books.stream()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        List<Integer> counts = countsMap.values().stream()
                .map(Long::intValue)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        return solve(counts);
    }

    private double solve(List<Integer> counts) {
        List<Integer> state = counts.stream()
                .filter(c -> c > 0)
                .sorted(Comparator.reverseOrder())
                .toList();

        if (state.isEmpty()) return 0.0;
        
        if (memo.containsKey(state)) return memo.get(state);

        double minCost = Double.MAX_VALUE;
        int maxGroupSize = state.size();

        for (int size = 1; size <= maxGroupSize; size++) {
            List<Integer> nextState = new ArrayList<>(state);
            for (int i = 0; i < size; i++) {
                nextState.set(i, nextState.get(i) - 1);
            }

            double currentTotal = getGroupPrice(size) + solve(nextState);
            minCost = Math.min(minCost, currentTotal);
        }

        memo.put(state, minCost);
        return minCost;
    }

    private double getGroupPrice(int size) {
        return (size * BOOK_PRICE) * (1.0 - DISCOUNTS[size - 1]);
    }
}