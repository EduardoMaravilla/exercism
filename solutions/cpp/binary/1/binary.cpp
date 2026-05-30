#include "binary.h"

namespace binary {

    int convert(const std::string& number) {
        int sum = 0;
        for (const char c : number) {
            if (c != '0' && c != '1') {
                return 0;
            }
            sum = sum * 2 + (c -'0');
        }
        return sum;
    }

}  // namespace binary
