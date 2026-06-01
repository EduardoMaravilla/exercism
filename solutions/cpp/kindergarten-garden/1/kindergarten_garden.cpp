#include "kindergarten_garden.h"

#include <algorithm>
#include <stdexcept>
#include <vector>

namespace kindergarten_garden {
    static const std::vector<std::string> students{
        "Alice", "Bob", "Charlie", "David",
        "Eve", "Fred", "Ginny", "Harriet",
        "Ileana", "Joseph", "Kincaid", "Larry"
    };

    static Plants get_plant(const char plant) {
        switch (plant) {
            case 'C':
                return Plants::clover;

            case 'G':
                return Plants::grass;

            case 'R':
                return Plants::radishes;

            case 'V':
                return Plants::violets;

            default:
                throw std::logic_error("Unknown plant");
        }
    }

    std::array<Plants, 4> plants(const std::string &garden, const std::string &name) {
        const auto it = std::find(students.begin(), students.end(), name);
        const auto pos = std::distance(students.begin(), it);
        const int first_index = static_cast<int>(pos) * 2;
        const int second_index = static_cast<int>(garden.find('\n') + 1);
        return {
            get_plant(garden[first_index]),
            get_plant(garden[first_index + 1]),
            get_plant(garden[second_index + first_index]),
            get_plant(garden[second_index + first_index + 1]),
        };
    }
} // namespace kindergarten_garden
