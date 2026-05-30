#include "perfect_numbers.h"

#include <cstdint>
#include <stdexcept>

namespace perfect_numbers {
    classification classify(int number) {
        if (number < 1) {
            throw std::domain_error("Error");
        }
        int64_t sum = 0;
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        if (sum == number) {
            return perfect;
        }
        if (sum > number) {
            return abundant;
        }
        return deficient;
    }
}  // namespace perfect_numbers
