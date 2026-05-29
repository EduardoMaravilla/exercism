#include "prime_factors.h"

namespace prime_factors {

    std::vector<long long>  of(long long x) {
        std::vector<long long> factors;
        long long num = 2;

        while (x > 1) {
            while ( x % num == 0 ) {
                factors.push_back(num);
                x /= num;
            }
            num++;
            if (num * num > x && x > 1) {
                factors.push_back(x);
                break;
            }
        }
        return factors;
    }

}  // namespace prime_factors
