#include "beer_song.h"

namespace beer_song {
    static  std::string bottle_count(const int n, const bool capitalize = false) {
        if (n == 0) return capitalize ? "No more" : "no more";
        return std::to_string(n);
    }

    static  std::string bottle_word(const int n) {
        return (n == 1) ? " bottle" : " bottles";
    }

    static  std::string next_number(const int n) {
        const int next = n - 1;
        if (next < 0) return "99";
        return bottle_count(next);
    }

    static  std::string action(const int n) {
        if (n == 0)
            return "Go to the store and buy some more, ";

        return "Take " + std::string(n == 1 ? "it" : "one") +
               " down and pass it around, ";
    }

    std::string verse(const int number) {
        return bottle_count(number, true) + bottle_word(number) +
               " of beer on the wall, " +
               bottle_count(number) + bottle_word(number) +
               " of beer.\n" +
               action(number) +
               next_number(number) + bottle_word(number - 1) +
               " of beer on the wall.\n";
    }

    std::string sing(int start, const int end) {
        std::string result;
        if (start > 99) {
            start = 99;
        }
        for (int i = start; i >= end; i--) {
            result.append(verse(i));
            if (i != end) {
                result.append("\n");
            }
        }
        return result;
    }
} // namespace beer_song
