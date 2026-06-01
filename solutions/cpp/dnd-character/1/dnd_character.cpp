#include "dnd_character.h"

#include <algorithm>
#include <random>
#include <vector>

namespace dnd_character {
    int modifier(const int constitution) {
        const int diff = constitution - 10;
        int mod = diff / 2;
        if (diff < 0 && diff % 2 != 0) {
            mod--;
        }
        return mod;
    }

    int ability() {
        static std::random_device rd;
        static std::mt19937 gen(rd());
        static std::uniform_int_distribution<> dist(1, 6);

        std::vector<int> dices;

        for (int i = 0; i < 4; ++i) {
            dices.push_back(dist(gen));
        }

        std::sort(dices.begin(), dices.end());

        return dices[1] + dices[2] + dices[3];
    }
} // namespace dnd_character
