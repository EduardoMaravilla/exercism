#include "robot_name.h"

#include <iomanip>
#include <random>

namespace robot_name {
    std::set<std::string> robot::robot_names{};
    std::string robot::generate_random_name() {
        static std::random_device rd;
        static std::mt19937 gen(rd());

        std::uniform_int_distribution<> letter_dist(0, 25);
        std::uniform_int_distribution<> num_dist(0, 999);

        const char letter1 = static_cast<char>('A' + letter_dist(gen));
        const char letter2 = static_cast<char>('A' + letter_dist(gen));
        const int num = num_dist(gen);

        std::ostringstream oss;
        oss << letter1
                << letter2
                << std::setw(3)
                << std::setfill('0')
                << num;
        return oss.str();
    }

    std::string robot::generate_unique_name() {
        std::string newName;
        do {
            newName = generate_random_name();
        } while (robot_names.count(newName) != 0);
        robot_names.insert(newName);
        return newName;
    }

    robot::robot() {
        robot_name = generate_unique_name();
    }

    std::string robot::name() const {
        return robot_name;
    }

    void robot::reset() {
        robot_name = generate_unique_name();
    }
} // namespace robot_name
