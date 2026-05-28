#pragma once
#include <cstdint>
#include <string>
#include <vector>

namespace secret_handshake {

std::vector<std::string> commands(int8_t secret);

}  // namespace secret_handshake
