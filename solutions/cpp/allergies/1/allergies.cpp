#include "allergies.h"

#include <map>

namespace allergies {
    std::map<std::string, int> allergies = {
        {"eggs", 1},
        {"peanuts", 2},
        {"shellfish", 4},
        {"strawberries", 8},
        {"tomatoes", 16},
        {"chocolate", 32},
        {"pollen", 64},
        {"cats", 128}
    };

    allergy_test::allergy_test(const int score) : score_(score) {
    }

    bool allergy_test::is_allergic_to(const std::string& item) const {
        return (allergies[item] & score_) != 0;
    }

    std::unordered_set<std::string> allergy_test::get_allergies() const {
        std::unordered_set<std::string > result;
        for (const auto&[fst, snd] : allergies) {
            if ((snd & score_ )!= 0) {
                result.insert(fst);
            }
        }
        return result;
    }
} // namespace allergies
