#include "word_count.h"

namespace word_count {
    std::map<std::string, int> words(const std::string& phrase) {
        std::map<std::string, int> result;
        std::string word;

        auto finalize_word = [&](std::string& w) {
            while (!w.empty() && w.back() == '\'') w.pop_back();
            if (!w.empty()) {
                result[w]++;
            }
            w.clear();
        };

        for (const char c : phrase) {
            char lower_c = static_cast<char>(std::tolower(static_cast<unsigned char>(c)));

            if (std::isalnum(lower_c) || (lower_c == '\'' && !word.empty())) {
                word += lower_c;
            } else if (!word.empty()) {
                finalize_word(word);
            }
        }
        finalize_word(word);

        return result;
    }
} // namespace word_count