#include "all_your_base.h"

#include <algorithm>
#include <stdexcept>

namespace all_your_base {
    std::vector<unsigned int> convert(unsigned int from, std::vector<unsigned int> digits, unsigned int to) {
        if (from < 2 || to < 2 || std::any_of(digits.begin(), digits.end(), [&](const unsigned int digit ) {
           return digit >= from;
        }) ) {
            throw std::invalid_argument("error");
        }
        if (digits.empty()) {
            return {};
        }
        unsigned int total = 0;
        for (const unsigned int digit : digits) {
            total = total * from + digit;
        }
        std::vector<unsigned int> result;
        while (total > 0) {
            result.push_back(total % to);
            total /= to;
        }

        std::reverse(result.begin(), result.end());

        return result;
    }
} // namespace all_your_base
