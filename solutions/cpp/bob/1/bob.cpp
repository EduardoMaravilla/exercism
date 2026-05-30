#include "bob.h"

#include <algorithm>

namespace bob {
    std::string answer1 = "Sure.";
    std::string answer2 = "Whoa, chill out!";
    std::string answer3 = "Calm down, I know what I'm doing!";
    std::string answer4 = "Fine. Be that way!";
    std::string answer5 = "Whatever.";

    std::string hey(std::string str) {
        str.erase(std::remove_if(str.begin(), str.end(), ::isspace), str.end());
        if (str.empty()) {
            return answer4;
        }
        const bool isQuestion = str.back() == '?';
        bool hasLetters = false;
        const bool isYell = std::all_of(
                          str.begin(),
                          str.end(),
                          [&](const unsigned char c) {
                              if (std::isalpha(c)) {
                                  hasLetters = true;
                                  return std::isupper(c);
                              }
                              return 1;
                          }
                      ) && hasLetters;
        if (isYell && isQuestion) {
            return answer3;
        }
        if (isQuestion) {
            return answer1;
        }
        if (isYell) {
            return answer2;
        }
        return answer5;
    }
} // namespace bob
