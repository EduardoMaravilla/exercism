#include "etl.h"

#include <locale>


namespace etl {

    std::map<char, int> transform(std::map<int, std::vector<char>> old) {
        std::map<char, int> result;
        for (const auto&[fst, snd] : old) {
            for (const char c : snd) {
                result[static_cast<char>(std::tolower(c))] = fst;
            }
        }
        return result;
    }

}  // namespace etl
