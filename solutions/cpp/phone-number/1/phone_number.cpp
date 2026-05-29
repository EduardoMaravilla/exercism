#include "phone_number.h"

#include <stdexcept>

namespace phone_number {
    phone_number::phone_number(const std::string& p_number) {
        std::string result;
        for (const char c : p_number) {
            if (std::isdigit(c)) {
                if (result.size() > 11) {
                    throw std::domain_error("Invalid number");
                }
                result += c;
            }else if (c == ' ' || c == '-' ||
                   c == '(' || c == ')' ||
                   c == '.' || c == '+') {
            }else {
                throw std::domain_error("Invalid number");
            }
        }
        if (result.size() > 11 || result.size()< 10 || (result.size() == 11 && result[0] != '1')) {
            throw std::domain_error("Invalid number");
        }
        phone_number_ = result.size() == 10 ? result : result.substr(1);
        if (phone_number_[0] == '0' || phone_number_[0] == '1' || phone_number_[3] == '0' || phone_number_[3] == '1') {
            throw std::domain_error("Invalid number");
        }
    }

    std::string phone_number::number() {
        return phone_number_;
    }
} // namespace phone_number
