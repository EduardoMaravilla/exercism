#include "knapsack.h"

namespace knapsack {
    int max(const int a, const int b) {
        return a > b ? a : b;
    }

    int maximum_value(const int max_weight, const std::vector<Item> &items) {
        if (items.empty() || max_weight == 0) {
            return 0;
        }
        std::vector<std::vector<int> > dp(items.size() + 1, std::vector<int>(max_weight + 1, 0));

        for (int i = 1; i <= static_cast<int>(items.size()); i++) {
            const int weight = items[i - 1].weight;
            const int value = items[i - 1].value;
            for (int w = 1; w <= max_weight; w++) {
                if (weight <= w) {
                    const int take = dp[i - 1][w -weight] + value;
                    const int skip = dp[i - 1][w];
                    dp[i][w] = max(take, skip);
                }else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[items.size()][max_weight];
    }
} // namespace knapsack
