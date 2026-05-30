#pragma once
#include <set>
#include <string>
#include <vector>

namespace anagram {

  class anagram {
    std::string word_;
    std::string sorted_word_;
    public:
    explicit anagram(const std::string &word);
    [[nodiscard]] std::set<std::string> matches(const std::vector<std::string>& words) const;
  };

}  // namespace anagram
