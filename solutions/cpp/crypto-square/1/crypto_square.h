#pragma once
#include <string>

namespace crypto_square {
    class cipher {
        std::string encode;
    public:
        explicit cipher(std::string plaintext);

        std::string normalized_cipher_text();
    };
} // namespace crypto_square
