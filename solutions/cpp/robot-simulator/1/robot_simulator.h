#pragma once
#include <string>
#include <utility>

namespace robot_simulator {
   enum Bearing {
      NORTH,
      SOUTH,
      EAST,
      WEST
   };

   class Robot {
      std::pair<int, int> position_;
      Bearing direction_;
      public:
      Robot();
      Robot(const std::pair<int, int> &position, Bearing direction);

      [[nodiscard]] std::pair<int, int> get_position() const;
      [[nodiscard]] Bearing get_bearing() const;
      void turn_left();
      void turn_right();
      void advance();

      void execute_sequence(std::string sequence);
   };

}  // namespace robot_simulator
