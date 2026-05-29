#pragma once
#include <string>

namespace date_independent {

    class clock {
        int hours_;
        int minutes_;

        clock(int hours, int minutes);

    public:
        static clock at(int hours, int minutes);

        [[nodiscard]] clock plus(int minutes) const;

        explicit operator std::string() const;

        bool operator==(const clock& other) const;
        bool operator!=(const clock& other) const;
    };

}