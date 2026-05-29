#pragma once
#include <set>
#include <string>

namespace robot_name {
    class robot {
    public:
        robot();

        [[nodiscard]] std::string name() const;

        void reset();

    private:
        std::string robot_name;
        static std::set<std::string> robot_names;
        static std::string generate_random_name();
        static std::string generate_unique_name();
    };
} // namespace robot_name
