#pragma once
#include <string_view>
#include <unordered_map>
#include <vector>

namespace parallel_letter_frequency {
    std::unordered_map<char, int> frequency(const std::vector<std::string_view>& texts);
}
