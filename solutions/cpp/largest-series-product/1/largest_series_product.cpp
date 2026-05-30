#include "largest_series_product.h"

#include <algorithm>
#include <limits>
#include <stdexcept>

namespace largest_series_product {

    size_t largest_product(const std::string& series, size_t n) {
        if (n < 1 || series.size() < n || std::any_of(series.begin(),series.end(), [](const char c) {
            return !isdigit(c);
        })) {
            throw std::domain_error("error");
        }
        size_t max_result = 0;
        for (size_t i = 0; i <= series.size() - n; ++i) {
            size_t temporal = 1;
            for (size_t j = i ; j < i + n; j++) {
                temporal *= static_cast<size_t>(series[j] - '0');
            }
            if (temporal > max_result) {
                max_result = temporal;
            }
        }
        return max_result;
    }

}  // namespace largest_series_product
