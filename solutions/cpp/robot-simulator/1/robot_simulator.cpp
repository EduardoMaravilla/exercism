#include "robot_simulator.h"

#include <stdexcept>

namespace robot_simulator {
    Robot::Robot() {
        position_ = std::make_pair(0, 0);
        direction_ = NORTH;
    }

    Robot::Robot(const std::pair<int, int> &position, const Bearing direction) : position_(position),
                                                                                 direction_(direction) {
    }

    std::pair<int, int> Robot::get_position() const {
        return position_;
    }

    Bearing Robot::get_bearing() const {
        return direction_;
    }

    void Robot::turn_left() {
        switch (direction_) {
            case NORTH:
                direction_ = WEST;
                break;
            case EAST:
                direction_ = NORTH;
                break;
            case SOUTH:
                direction_ = EAST;
                break;
            case WEST:
                direction_ = SOUTH;
                break;
            default:
                throw std::invalid_argument{"invalid direction"};
        }
    }

    void Robot::turn_right() {
        switch (direction_) {
            case NORTH:
                direction_ = EAST;
                break;
            case EAST:
                direction_ = SOUTH;
                break;
            case SOUTH:
                direction_ = WEST;
                break;
            case WEST:
                direction_ = NORTH;
                break;
            default:
                throw std::invalid_argument{"invalid direction"};
        }
    }

    void Robot::advance() {
        int x = position_.first;
        int y = position_.second;
        switch (direction_) {
            case NORTH:
                y += 1;
                break;
            case EAST:
                x += 1;
                break;
            case SOUTH:
                y -= 1;
                break;
            case WEST:
                x -= 1;
                break;
            default:
                throw std::invalid_argument{"invalid direction"};
        }
        position_ = std::make_pair(x, y);
    }

    void Robot::execute_sequence(std::string sequence) {
        for (const char c: sequence) {
            switch (c) {
                case 'L':
                    turn_left();
                    break;
                case 'R':
                    turn_right();
                    break;
                case 'A':
                    advance();
                    break;
                default:
                    throw std::invalid_argument{"invalid sequence"};
            }
        }
    }
} // namespace robot_simulator
