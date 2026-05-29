#include "series.h"

#include <stdexcept>

namespace series {
    std::vector<std::string> slice(const std::string& series, const size_t length) {
        if (series.empty() || series.length() < length || length <= 0) {
            throw std::domain_error("invalid data");
        }
        std::vector<std::string> result;
        result.reserve(series.length() - length);
        for (size_t i = 0; i <= series.length() - length; i++) {
            result.push_back(series.substr(i, length));
        }
        return result;
    }
} // namespace series
