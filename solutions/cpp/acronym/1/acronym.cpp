#include "acronym.h"

#include <cctype>
#include <string>

namespace acronym {

    std::string acronym(const std::string& str) {
        std::string result;
        bool new_word = true;

        for (const char c : str) {
            if (std::isalpha(c)) {
                if (new_word) {
                    result += static_cast<char>(std::toupper(c));
                }
                new_word = false;
            }
            else if (c != '\'') {
                new_word = true;
            }
        }

        return result;
    }

}