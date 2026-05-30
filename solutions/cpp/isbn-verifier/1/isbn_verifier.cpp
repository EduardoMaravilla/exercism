#include "isbn_verifier.h"

namespace isbn_verifier {

    bool is_valid(const std::string& isbn) {
        int sum = 0;
        int i = 0;
        for (const char c : isbn) {
            if ((c >= '0' && c <= '9') || c == 'X') {
                if (c == 'X' && i < 9) {
                    return false;
                }
                const int value = (c == 'X') ? 10 : c - '0';
                sum += value * (10 - i++);
            }else if (c == '-'){}
            else return false;
        }
        if ( i != 10) {
            return false;
        }
        return sum % 11 == 0;
    }

}  // namespace isbn_verifier
