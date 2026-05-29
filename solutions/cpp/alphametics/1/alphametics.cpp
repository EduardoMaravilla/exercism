#include "alphametics.h"
#include <algorithm>
#include <vector>
#include <string>
#include <set>
#include <map>

namespace alphametics {

std::optional<std::map<char, int>> solve(const std::string& puzzle) {
    std::string p = puzzle;
    p.erase(std::remove_if(p.begin(), p.end(), ::isspace), p.end());

    std::vector<std::string> words;
    std::string token;
    for (const char c : p) {
        if (c == '+' || c == '=') {
            if (!token.empty()) {
                words.push_back(token);
                token.clear();
            }
        } else {
            token += c;
        }
    }
    if (!token.empty()) {
        words.push_back(token);
    }

    if (words.size() <= 2) return std::nullopt;

    const std::string& lastWord = words.back();
    for (size_t i = 0; i < words.size() - 1; ++i) {
        if (words[i].length() > lastWord.length()) return std::nullopt;
    }

    std::set<char> charSet;
    bool can_be_zero[256];
    std::fill(std::begin(can_be_zero), std::end(can_be_zero), true);

    for (const auto& w : words) {
        for (char c : w) charSet.insert(c);
        if (w.length() > 1) {
            can_be_zero[static_cast<unsigned char>(w[0])] = false;
        }
    }

    const std::vector chars(charSet.begin(), charSet.end());
    if (chars.size() > 10) return std::nullopt;

    std::vector digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    int char_to_digit[256] = {};

    do {
        bool valid = true;
        for (size_t i = 0; i < chars.size(); ++i) {
            if (digits[i] == 0 && !can_be_zero[static_cast<unsigned char>(chars[i])]) {
                valid = false;
                break;
            }
            char_to_digit[static_cast<unsigned char>(chars[i])] = digits[i];
        }

        if (valid) {
            unsigned long long sum = 0;
            for (size_t i = 0; i < words.size() - 1; ++i) {
                unsigned long long value = 0;
                for (const char c : words[i]) {
                    value = value * 10 + char_to_digit[static_cast<unsigned char>(c)];
                }
                sum += value;
            }

            unsigned long long answer = 0;
            for (const char c : lastWord) {
                answer = answer * 10 + char_to_digit[static_cast<unsigned char>(c)];
            }

            if (sum == answer) {
                std::map<char, int> solution;
                for (char c : chars) {
                    solution[c] = char_to_digit[static_cast<unsigned char>(c)];
                }
                return solution;
            }
        }

        std::reverse(digits.begin() + static_cast<std::ptrdiff_t>(chars.size()), digits.end());

    } while (std::next_permutation(digits.begin(), digits.end()));

    return std::nullopt;
}

}  // namespace alphametics