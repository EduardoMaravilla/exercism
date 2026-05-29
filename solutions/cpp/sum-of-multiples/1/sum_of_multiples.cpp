#include "sum_of_multiples.h"

namespace sum_of_multiples {

    int to(const std::vector<int>& numbers, const int limit) {
        int result = 0;
        for (int i = 0; i < limit; i++) {
            for (const int number : numbers) {
                if (number != 0 && i % number == 0) {
                    result += i;
                    break;
                }
            }
        }
        return result;
    }

}  // namespace sum_of_multiples
