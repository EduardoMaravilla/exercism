#include "anagram.h"

#include <algorithm>
#include <utility>

namespace anagram {
    static std::string to_lower(std::string word) {
        std::transform(word.begin(), word.end(), word.begin(), [](unsigned char c) {
            return std::tolower(c);
        });
        return word;
    }

    static std::string sorted_word(std::string word) {
        std::sort(word.begin(), word.end());
        return word;
    }

    anagram::anagram(const std::string &word) {
        word_ = to_lower(word);
        sorted_word_ = sorted_word(word_);
    }

    std::set<std::string> anagram::matches(const std::vector<std::string> &words) const {
        std::set<std::string> result{};

        for (const auto &word: words) {
            const std::string current_word = to_lower(word);
            const std::string current_sort = sorted_word(current_word);
            if (current_sort == sorted_word_ && current_word != word_) {
                result.insert(word);
            }
        }
        return result;
    }
} // namespace anagram
