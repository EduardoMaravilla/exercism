#pragma once
#include <string>
#include <unordered_set>
namespace allergies {

  class allergy_test {
    public:
    int score_;
    explicit allergy_test(int score);
    [[nodiscard]] bool is_allergic_to(const std::string& item) const;
    [[nodiscard]] std::unordered_set<std::string> get_allergies() const;
  };

}  // namespace allergies
