#include "roman_numerals.h"

#include <vector>

namespace roman_numerals {
    static std::vector<std::string> romanSymbols = {
        "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
    };
    static std::vector arabicValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    std::string convert(int number) {
        std::string result;
        for (size_t i = 0; i < arabicValues.size(); i++ ) {
            while (number >= arabicValues[i]) {
                result.append(romanSymbols[i]);
                number -= arabicValues[i];
            }
        }
        return result;
    }
} // namespace roman_numerals
