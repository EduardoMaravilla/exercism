#pragma once
#include <array>
#include <string>

namespace kindergarten_garden {
    enum class Plants {
        clover, grass,
        violets,
        radishes
    };

    std::array<Plants,4> plants(const std::string &garden, const std::string& name);
} // namespace kindergarten_garden
