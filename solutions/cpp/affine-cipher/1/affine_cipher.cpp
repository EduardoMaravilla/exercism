#include "affine_cipher.h"

#include <numeric>
#include <sstream>
#include <stdexcept>

namespace affine_cipher {
    static const int M = 26;

    static int find_multiplicative_inverse(const int a) {
        for (int i = 1; i < M; i++) {
            if (a * i % M == 1) {
                return i;
            }
        }
        throw std::invalid_argument("invalid multiplicative");
    }

    std::string encode(const std::string &input, const int c1, const int c2) {
        if (std::gcd(c1, M) != 1) {
            throw std::invalid_argument("error");
        }
        std::stringstream ss;
        int control = 0;
        for (const char c: input) {
            const auto current = static_cast<unsigned char>(tolower(c));
            if (control > 0 && control % 5 == 0 && isalnum(c)) {
                ss << ' ';
                control= 0;
            }
            if (isalpha(c)) {
                ss << static_cast<unsigned char>('a' + (c1 * (current - 'a') + c2) % M);
                control++;
            } else if (current == ' ' || current == '.' || current == ',') {
            } else {
                ss << current;
                control++;
            }
        }
        return ss.str();
    }

    std::string decode(const std::string& input, const int c1, const int c2) {
        if (std::gcd(c1, M) != 1) {
            throw std::invalid_argument("error");
        }
        std::stringstream ss;
        const int inverse = find_multiplicative_inverse(c1);
        for (const char c: input) {
            const auto current = static_cast<unsigned char>(tolower(c));
            if (isalpha(c)) {
                int idx = (inverse * ((current - 'a') - c2 + M)) % M;
                if (idx < 0) {
                    idx += M;
                }
                ss << static_cast<unsigned char>('a' + idx);
            }else if (current == ' ') {            }
            else {
                ss << current;
            }
        }
        return ss.str();
    }
} // namespace affine_cipher
