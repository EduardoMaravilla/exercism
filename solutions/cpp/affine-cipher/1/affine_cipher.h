#ifndef AFFINE_CIPHER_H
#define AFFINE_CIPHER_H
#include <string>

namespace affine_cipher {
    std::string encode(const std::string& input, int c1, int c2);

    std::string decode(const std::string& input, int c1, int c2);
} // namespace affine_cipher

#endif  // AFFINE_CIPHER_H
