#include "two_bucket.h"

#include <algorithm>
#include <numeric>
#include <stdexcept>

namespace two_bucket {
    measure_result measure(int bucket1_capacity, int bucket2_capacity,
                           int target_volume, bucket_id start_bucket) {
        if ((target_volume > bucket1_capacity && target_volume > bucket2_capacity) ||
            target_volume % std::gcd(bucket1_capacity, bucket2_capacity) != 0) {
            throw std::domain_error("Error");
        }

        int b1;
        int b2;

        int b1_cap;
        int b2_cap;

        bucket_id b1_id;
        bucket_id b2_id;

        if (start_bucket == bucket_id::one) {
            b1 = bucket1_capacity;
            b2 = 0;

            b1_cap = bucket1_capacity;
            b2_cap = bucket2_capacity;

            b1_id = bucket_id::one;
            b2_id = bucket_id::two;
        } else {
            b1 = bucket2_capacity;
            b2 = 0;

            b1_cap = bucket2_capacity;
            b2_cap = bucket1_capacity;

            b1_id = bucket_id::two;
            b2_id = bucket_id::one;
        }

        int moves = 1;

        if (b2_cap == target_volume) {
            b2 = b2_cap;
            ++moves;
        }

        while (b1 != target_volume && b2 != target_volume) {
            if (b1 == 0) {
                b1 = b1_cap;
            } else if (b2 == b2_cap) {
                b2 = 0;
            } else {
                const int amount = std::min(b1, b2_cap - b2);

                b1 -= amount;
                b2 += amount;
            }
            ++moves;
        }

        if (b1 == target_volume) {
            return {moves, b1_id, b2};
        }
        return {moves, b2_id, b1};
    }
} // namespace two_bucket
