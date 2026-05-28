import java.util.*;
class ChangeCalculator {
    final List<Integer> currencyCoins;

    ChangeCalculator(List<Integer> currencyCoins) {
        Collections.sort(currencyCoins);
        Collections.reverse(currencyCoins);
        this.currencyCoins = currencyCoins;
    }

    List<Integer> computeMostEfficientChange(int grandTotal) {
        if (grandTotal < 0) {
            throw new IllegalArgumentException("Negative totals are not allowed.");
        }

         List<Integer> result = bestChange(grandTotal);

        if (getSum(result) != grandTotal) {
            throw new IllegalArgumentException(
                    String.format("The total %d cannot be represented in the given currency.",
                            grandTotal));
        }
        if (result.size() > 1) Collections.sort(result);
        return result;
    }

    List<Integer> bestChange(int desiredTotal) {
        if (desiredTotal==0) return List.of();
        if (currencyCoins.contains(desiredTotal)) return List.of(desiredTotal);

        List<Integer> currentBest = new ArrayList<>();
        int currentBestSize = Integer.MAX_VALUE;

        for (int coin: currencyCoins) {
            if (coin < desiredTotal && desiredTotal / coin < currentBestSize) {
                List<Integer> best = new ArrayList<>();
                best.add(coin);
                best.addAll(bestChange(desiredTotal - coin));
                if (getSum(best) == desiredTotal
                        && (currentBestSize == Integer.MAX_VALUE || best.size() < currentBestSize)) {
                    currentBest = best;
                    currentBestSize = currentBest.size();
                }
            }
        }
        return currentBest;
    }

    private static int getSum(List<Integer> list) {
        return list.stream().reduce(0, Integer::sum);
    }
}