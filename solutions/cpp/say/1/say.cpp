#include "say.h"

#include <stdexcept>
#include <vector>

static const std::vector<std::string> basics = {
    "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
    "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
};

static const std::vector<std::string> tens = {
    "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
};

static const std::vector<std::string> thousands = {"thousand", "million", "billion"};

namespace say {
    std::string in_english(const int64_t number) {
        if (number < 0 || number > 999999999999) {
            throw std::domain_error("Error");
        }
        if (number < 20) {
            return basics[number];
        }
        if (number < 100) {
            return tens[number /10 - 2] + (number % 10 == 0 ? "": "-" + basics[number % 10]);
        }
        if (number < 1000) {
            return basics[number /100] + " hundred" + (number % 100 == 0 ? "": " " + in_english(number % 100));
        }
        for (int i = 2; i >= 0; i--) {
            int64_t divider = 1;
            for (int j = 0; j < (i + 1) * 3; j++) {
                divider *= 10;
            }
            if (number >= divider) {
                return in_english(number/divider) + " " + thousands[i] + (number % divider == 0 ? "": " " + in_english(number%divider));
            }
        }
        return "";
    }

}  // namespace say
