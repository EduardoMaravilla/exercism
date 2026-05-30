#include "isogram.h"

namespace isogram {

    bool is_isogram(std::string input) {
        bool seen[26] = {false};
        for (const char c : input) {
            if (isalpha(c)) {
                const int index = tolower(c) - 'a';
                if (seen[index]) {
                    return false;
                }
                seen[index] = true;
            }
        }
        return true;
    }

}  // namespace isogram
