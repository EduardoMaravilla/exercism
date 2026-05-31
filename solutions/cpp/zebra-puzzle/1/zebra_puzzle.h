#pragma once

#include <string>

namespace zebra_puzzle {
    enum house_color {
        RED,
        GREEN,
        IVORY,
        YELLOW,
        BLUE
    };

    enum drink {
        COFFEE,
        TEA,
        MILK,
        ORANGE_JUICE,
        WATER
    };

    enum pet {
        DOG,
        SNAILS,
        FOX,
        HORSE,
        ZEBRA
    };

    enum nationality {
        ENGLISHMAN,
        SPANIARD,
        UKRAINIAN,
        NORWEGIAN,
        JAPANESE
    };

    enum hobby {
        DANCING,
        PAINTING,
        READING,
        FOOTBALL,
        CHESS
    };

    struct Solution {
        std::string drinksWater;
        std::string ownsZebra;
    };

    Solution solve();
} // namespace zebra_puzzle
