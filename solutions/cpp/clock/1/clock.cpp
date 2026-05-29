#include "clock.h"

#include <iomanip>
#include <sstream>

namespace date_independent {
    clock::clock(const int hours, const int minutes) {
        int total_minutes = (hours * 60 + minutes) % 1440;

        if (total_minutes < 0) {
            total_minutes += 1440;
        }

        hours_ = total_minutes / 60;
        minutes_ = total_minutes % 60;
    }

    clock clock::at(const int hours, const int minutes) {
        return {hours, minutes};
    }

    clock clock::plus(const int minutes) const {
        return {hours_, minutes_ + minutes};
    }

    bool clock::operator==(const clock &other) const {
        return minutes_ == other.minutes_ && hours_ == other.hours_;
    }

    bool clock::operator!=(const clock &other) const {
        return !(*this == other);
    }

    clock::operator std::string() const {
        std::ostringstream oss;

        oss << std::setw(2)
                << std::setfill('0')
                << hours_
                << ":"
                << std::setw(2)
                << std::setfill('0')
                << minutes_;

        return oss.str();
    }
} // namespace date_independent
