#include "diamond.h"

namespace diamond {

    std::vector<std::string> rows(char letter) {
        const size_t diamond_length = (letter - 'A' + 1) * 2 - 1;
        std::vector<std::string> diamond(diamond_length, std::string(diamond_length, ' '));
        int count = 0;
        for (char letter_temp = 'A'; letter_temp <= letter ; letter_temp++ ) {
            if ( letter_temp == 'A') {
                const size_t spaces = letter - letter_temp;
                diamond[count][spaces] = letter_temp;
                count++;
            }else if (letter_temp < letter) {
                const size_t external_spaces = letter - letter_temp;
                const size_t internal_spaces = diamond_length - 2 - (2 * external_spaces);
                diamond[count][external_spaces] = letter_temp;
                diamond[count][external_spaces + 1 + internal_spaces] = letter_temp;
                count++;
            }else {
                diamond[count][0] = letter_temp;
                diamond[count][diamond_length - 1] = letter_temp;
                count++;
            }
        }
        for (int i = count - 2; i >= 0; i--) {
            diamond[count++] = diamond[i];
        }
        return diamond;
    }

}  // namespace diamond
