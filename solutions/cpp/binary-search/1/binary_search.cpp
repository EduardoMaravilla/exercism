#include "binary_search.h"

#include <stdexcept>

namespace binary_search {

    size_t find(const std::vector<int>& numbers, size_t value) {
        if (numbers.empty()) {
            throw std::domain_error("Number not found");
        }

        size_t left = 0;
        size_t right = numbers.size() - 1;

        while (left <= right) {
            size_t middle = left + (right - left) / 2;
            if (numbers[middle] < static_cast<int>(value)) {
                left = middle + 1;
            } else if (numbers[middle] > static_cast<int>(value)) {
                if (middle == 0) break;
                right = middle - 1;
            } else {
                return middle;
            }
        }

        throw std::domain_error("Number not found");
    }

}  // namespace binary_search
