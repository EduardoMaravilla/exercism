#include "sieve.h"

namespace sieve {

    std::vector<int> primes(const int limit) {
        if (limit < 2) {
            return {};
        }
        std::vector sieve(limit + 1 ,true);
        std::vector<int> result;
        sieve[0] = false;
        sieve[1] = false;
        for (int i = 2; i <= limit; i++) {
            if (sieve[i]) {
                result.push_back(i);
                if (i * i <= limit) {
                    for (int j = i * i; j <= limit; j += i) {
                        sieve[j] = false;
                    }
                }
            }
        }
        return result;
    }

}  // namespace sieve
