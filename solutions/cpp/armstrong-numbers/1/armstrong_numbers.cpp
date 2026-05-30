#include "armstrong_numbers.h"

#include <cmath>

namespace armstrong_numbers {

    bool is_armstrong_number(const int number) {
        int temp = number;
        int length = 0;
        int sum = 0;

        do {
            length++;
            temp /= 10;
        }while (temp != 0);
        temp = number;

        do {
            const int digit = temp % 10;
            sum += static_cast<int>(std::pow(digit, length));
            temp /= 10;
        } while (temp != 0);
       return sum == number;
    }

}  // namespace armstrong_numbers