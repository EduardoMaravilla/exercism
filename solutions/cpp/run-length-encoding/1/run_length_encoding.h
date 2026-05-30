#pragma once
#include <string>

namespace run_length_encoding {

 std::string encode(const std::string &str);
 std::string decode(const std::string& str);

}  // namespace run_length_encoding
