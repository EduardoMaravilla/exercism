#include "run_length_encoding.h"

namespace run_length_encoding {

    std::string encode(const std::string &str) {
        if (str.empty()) {
            return "";
        }
        std::string result;
        char prev = str[0];
        int count = 1;
        for (size_t i = 1; i < str.length(); i++) {
            char current = str[i];
            if (current == prev) {
                count++;
            }else {
                std::string temp = count > 1 ? std::to_string(count) : "";
                result.append(temp);
                result.push_back(prev);
                prev = current;
                count = 1;
            }
        }
        std::string temp = count > 1 ? std::to_string(count) : "";
        result.append(temp);
        result.push_back(prev);
        return result;
    }
    std::string decode(const std::string& str) {
        std::string result;
        std::string buffer;
        for (const char c : str) {
            if (isdigit(c)) {
                buffer.push_back(c);
            }else {
                const int count = buffer.empty() ? 1 : std::stoi(buffer);
                result.append(std::string(count, c));
                buffer.clear();
            }
        }
        return result;
    }

}  // namespace run_length_encoding
