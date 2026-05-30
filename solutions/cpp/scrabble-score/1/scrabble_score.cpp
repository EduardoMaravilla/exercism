#include "scrabble_score.h"

namespace scrabble_score {
    static const int LETTER_SCORES[26] = {
        /* a b c d e f g h i j k l m */
        1,3,3,2,1,4,2,4,1,8,5,1,3,
     /* n o p q r s t u v w x y z */
        1,1,3,10,1,1,1,1,4,4,8,4,10
    };
    int score(const std::string& word) {
        int points = 0;
        for (const char c: word) {
            const char temp = static_cast<char>(tolower(c));
            if (temp >= 'a' && temp <= 'z') {
              points += LETTER_SCORES[temp - 'a'];
            }
        }
        return points;
    }
} // namespace scrabble_score
