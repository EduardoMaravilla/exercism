#if !defined(ALPHAMETICS_H)
#define ALPHAMETICS_H

#include <map>
#include <string>
#include <optional>

namespace alphametics {

 std::optional<std::map<char, int>> solve(const std::string& puzzle);

}  // namespace alphametics

#endif