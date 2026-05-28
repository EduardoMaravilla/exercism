#include "secret_handshake.h"

namespace secret_handshake {

    static const std::string actions[] = {
        "wink",
        "double blink",
        "close your eyes",
        "jump"
    };

    std::vector<std::string> commands(int8_t secret) {
        if (secret == 0) {
            return {};
        }
        std::vector<std::string> result;
        result.reserve(4);


        if ((secret & 0x10) == 0) {

            for (int i = 0; i < 4; i++) {
                if (secret & (1 << i)) {
                    result.push_back(actions[i]);
                }
            }

        } else {

            for (int i = 3; i >= 0; i--) {
                if (secret & (1 << i)) {
                    result.push_back(actions[i]);
                }
            }
        }

        return result;
    }

}  // namespace secret_handshake
