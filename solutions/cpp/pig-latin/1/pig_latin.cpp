#include "pig_latin.h"

#include <sstream>
#include <vector>

namespace pig_latin {
    static std::vector<std::string> split(const std::string &text) {
        std::vector<std::string> words;
        std::stringstream ss(text);

        std::string word;
        while (ss >> word) {
            words.push_back(word);
        }
        return words;
    }

    static bool is_vowel(const char c) {
        static constexpr std::string_view vowels = "aeiou";
        return vowels.find(c) != std::string_view::npos;
    }

    static bool is_vowel_sound(const char c1, const char c2) {
        if (is_vowel(c1)) return true;

        if (c1 == 'x' || c1 == 'y') {
            return !is_vowel(c2);
        }

        return false;
    }

    std::string translate(const std::string &input) {
        std::string trans;
        auto words = split(input);

        for (const auto& word : words) {

            char c1 = word[0];
            char c2 = (word.size() > 1) ? word[1] : '\0';

            if (is_vowel_sound(c1, c2))
                trans.append(word).append("ay ");
            else if (word.rfind("thr", 0) == 0)
                trans.append(word.substr(3)).append("thray ");
            else if (word.rfind("ch", 0) == 0)
                trans.append(word.substr(2)).append("chay ");
            else if (word.rfind("rh", 0) == 0)
                trans.append(word.substr(2)).append("rhay ");
            else if (word.rfind("th", 0) == 0)
                trans.append(word.substr(2)).append("thay ");
            else if (word.rfind("qu", 0) == 0)
                trans.append(word.substr(2)).append("quay ");
            else if (word.rfind("squ", 0) == 0)
                trans.append(word.substr(3)).append("squay ");
            else if (word.rfind("sch", 0) == 0)
                trans.append(word.substr(3)).append("schay ");
            else {
                trans.append(word.substr(1));
                trans.push_back(word[0]);
                trans.append("ay ");
            }
        }

        if (!trans.empty())
            trans.pop_back();

        return trans;
    }
} // namespace pig_latin
