import java.util.List;

public class Knapsack {
    int maximumValue(int maxWeight, List<Item> items) {
        int n = items.size();
        int[][] dp = new int[n+1][maxWeight + 1];
        for (int i = 1; i <= n; i++) {
            int weight = items.get(i - 1).weight;
            int value = items.get(i - 1).value;
            for(int w = 0; w <= maxWeight; w++){
                if (weight <= w){
                    dp[i][w] = Math.max(dp[i-1][w],dp[i-1][w - weight] + value);
                }else {
                    dp[i][w] = dp[i-1][w];
                }
            }
        }
        return dp[n][maxWeight];
    }
}