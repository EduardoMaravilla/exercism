#pragma once
#include <string>

namespace phone_number {
    class phone_number {
        std::string phone_number_;

    public:
        explicit phone_number(const std::string& p_number);

        std::string number();
    };
} // namespace phone_number
