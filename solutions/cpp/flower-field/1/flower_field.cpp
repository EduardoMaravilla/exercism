#include "flower_field.h"

namespace flower_field {
    static const int directions[8][2] = {
        {-1, 0}, {1, 0},
        {0, -1}, {0, 1},
        {-1, -1}, {-1, 1},
        {1, -1}, {1, 1}
    };

    static int count_mines(const size_t row, const size_t column, const std::vector<std::string>& garden) {
        int mines = 0;
        for (const auto direction : directions) {
            const size_t new_row = row + direction[0];
            const size_t new_column = column + direction[1];
            if (new_row < garden.size()) {
                const std::string& new_garden = garden[new_row];
                if (new_column < new_garden.size() && new_garden[new_column] == '*') {
                    mines++;
                }
            }
        }
        return mines;
    }

    std::vector<std::string> annotate(const std::vector<std::string> &flower_field) {
        std::vector<std::string> garden = flower_field;
        for (size_t i = 0; i < flower_field.size(); i++) {
            for (size_t j = 0; j < flower_field[0].size(); j++) {
                if (garden[i][j] == ' ') {
                    const int mines = count_mines(i,j,garden);
                    garden[i][j] = mines > 0 ? static_cast<char>('0' + mines): ' ';
                }
            }
        }

        return garden;
    }
} // namespace flower_field
