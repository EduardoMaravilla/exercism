#include "rail_fence_cipher.h"

#include <vector>

namespace rail_fence_cipher {
    std::vector<int> get_rail_pattern(const size_t string_length, const int num_rails) {
        int rail = 0;
        int direction = 1;
        std::vector<int> pattern(string_length, 0);
        for (size_t i = 0; i < string_length; i++) {
            pattern[i] = rail;
            rail += direction;
            if (rail == 0 || rail == num_rails - 1) {
                direction *= -1;
            }
        }
        return pattern;
    }

    std::vector<int> count_rails(const std::vector<int> &rail_pattern, const size_t string_length,
                                 const int num_rails) {
        std::vector<int> counts(num_rails, 0);
        for (size_t i = 0; i < string_length; i++) {
            counts[rail_pattern[i]]++;
        }
        return counts;
    }

    std::vector<int> build_offsets(const std::vector<int> &counts, const int num_rails) {
        std::vector<int> offsets(num_rails, 0);
        offsets[0] = 0;
        for (int i = 1; i < num_rails; i++) {
            offsets[i] = offsets[i - 1] + counts[i - 1];
        }
        return offsets;
    }

    std::string encode(const std::string &plaintext, int num_rails) {
        if (num_rails <= 1) {
            return plaintext;
        }
        const std::vector<int> rail_pattern = get_rail_pattern(plaintext.length(), num_rails);
        const std::vector<int> counts = count_rails(rail_pattern, plaintext.length(), num_rails);
        std::vector<int> offsets = build_offsets(counts, num_rails);
        std::string result(plaintext.length(), ' ');
        for (size_t i = 0; i < plaintext.length(); i++) {
            result[offsets[rail_pattern[i]]++] = plaintext[i];
        }
        return result;
    }

    std::string decode(const std::string &ciphertext, int num_rails) {
        if (num_rails <= 1) {
            return ciphertext;
        }
        const std::vector<int> rail_pattern = get_rail_pattern(ciphertext.length(), num_rails);
        const std::vector<int> counts = count_rails(rail_pattern, ciphertext.length(), num_rails);
        std::vector<int> offsets = build_offsets(counts, num_rails);
        std::string result(ciphertext.length(), ' ');
        for (size_t i = 0; i < ciphertext.length(); i++) {
            result[i] = ciphertext[offsets[rail_pattern[i]]++];
        }
        return result;
    }
} // namespace rail_fence_cipher
