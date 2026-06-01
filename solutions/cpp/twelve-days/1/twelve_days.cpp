#include "twelve_days.h"
#include <vector>
#include <string>
#include <sstream>

namespace twelve_days {

    std::string recite(int start_verse, int end_verse) {
        const std::vector<std::string> days = {
            "first", "second", "third", "fourth", "fifth", "sixth",
            "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"
        };

        const std::vector<std::string> gifts = {
            "a Partridge in a Pear Tree.\n",
            "two Turtle Doves, ",
            "three French Hens, ",
            "four Calling Birds, ",
            "five Gold Rings, ",
            "six Geese-a-Laying, ",
            "seven Swans-a-Swimming, ",
            "eight Maids-a-Milking, ",
            "nine Ladies Dancing, ",
            "ten Lords-a-Leaping, ",
            "eleven Pipers Piping, ",
            "twelve Drummers Drumming, "
        };

        std::stringstream result;

        for (int v = start_verse; v <= end_verse; ++v) {
            result << "On the " << days[v - 1] << " day of Christmas my true love gave to me: ";

            if (v == 1) {
                result << gifts[0];
            } else {
                for (int i = v - 1; i > 0; --i) {
                    result << gifts[i];
                }
                result << "and " << gifts[0];
            }

            if (v < end_verse) {
                result << "\n";
            }
        }

        return result.str();
    }

} // namespace twelve_days