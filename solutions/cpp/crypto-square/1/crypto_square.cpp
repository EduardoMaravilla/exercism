#include "crypto_square.h"

#include <algorithm>
#include <cmath>

namespace crypto_square {
    cipher::cipher(std::string plaintext) {
        plaintext.erase(
            std::remove_if(plaintext.begin(), plaintext.end(), [](const unsigned char c) {
                return !std::isalnum(c);
            }),
            plaintext.end()
        );
        if (plaintext.empty()) {
            encode.append("");
            return;
        }
        const int columns = static_cast<int>(std::ceil(sqrt(static_cast<double>(plaintext.size()))));
        const int rows = static_cast<int>(std::ceil(static_cast<double>(plaintext.size())/columns ));
        const int result_size = rows * columns + (columns - 1) ;
        int pos = 0;
        encode.reserve(result_size);
        for (int i = 0; i < result_size; i++) {
            encode.push_back(' ');
        }
        for (int col = 0; col < columns; col++) {
            if (col > 0) {
                pos++;
            }
            for (int row = 0; row < rows; row++) {
                if (const int index = row * columns + col; index < static_cast<int>(plaintext.size())) {
                    encode[pos++] = static_cast<char>(std::tolower(plaintext[index]));
                }else {
                    pos++;
                }
            }
        }
    }

    std::string cipher::normalized_cipher_text() {
        return encode;
    }

} // namespace crypto_square
