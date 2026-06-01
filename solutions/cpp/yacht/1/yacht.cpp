#include "yacht.h"

#include <algorithm>
#include <numeric>

namespace yacht {
    static bool all_match(const std::vector<int> &dices) {
        return std::all_of(
            dices.begin(),
            dices.end(),
            [&](const int x) { return x == dices[0]; }
        );
    }

    static int sum_matching_dice(const std::vector<int> &dices, const int value) {
        return std::accumulate(
            dices.begin(),
            dices.end(),
            0,
            [value](const int acc, const int die) {
                return acc + (die == value ? die : 0);
            }
        );
    }

    static bool is_full_house(const std::vector<int> &dices) {
        const bool pair_then_three =
                dices[0] == dices[1] &&
                dices[1] != dices[2] &&
                dices[2] == dices[3] &&
                dices[3] == dices[4];
        const bool three_then_pair =
                dices[0] == dices[1] &&
                dices[1] == dices[2] &&
                dices[2] != dices[3] &&
                dices[3] == dices[4];
        return pair_then_three || three_then_pair;
    }

    static int four_of_a_kind(const std::vector<int> &dices) {
        if (dices[0] == dices[3]) {
            return dices[0] * 4;
        }

        if (dices[1] == dices[4]) {
            return dices[1] * 4;
        }
        return 0;
    }

    static bool is_straight(const std::vector<int> &dices) {
        return std::adjacent_find(
                   dices.begin(),
                   dices.end(),
                   [](const int a, const int b) {
                       return b != a + 1;
                   }
               ) == dices.end();
    }

    int score(std::vector<int> dices, const std::string &election) {
        std::sort(dices.begin(), dices.end());
        if (election == "yacht") {
            return all_match(dices) ? YACHT_SCORE : 0;
        }
        if (election == "ones") {
            return sum_matching_dice(dices, 1);
        }
        if (election == "twos") {
            return sum_matching_dice(dices, 2);
        }
        if (election == "threes") {
            return sum_matching_dice(dices, 3);
        }
        if (election == "fours") {
            return sum_matching_dice(dices, 4);
        }
        if (election == "fives") {
            return sum_matching_dice(dices, 5);
        }
        if (election == "sixes") {
            return sum_matching_dice(dices, 6);
        }
        if (election == "full house") {
            return is_full_house(dices) ? std::accumulate(dices.begin(), dices.end(), 0) : 0;
        }
        if (election == "four of a kind") {
            return four_of_a_kind(dices);
        }
        if (election == "little straight") {
            return is_straight(dices) && dices[0] == 1 ? STRAIGHT_SCORE : 0;
        }
        if (election == "big straight") {
            return is_straight(dices) && dices[0] == 2 ? STRAIGHT_SCORE : 0;
        }
        if (election == "choice") {
            return std::accumulate(dices.begin(), dices.end(), 0);
        }
        return 0;
    }
} // namespace yacht
