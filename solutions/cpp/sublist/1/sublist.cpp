#include "sublist.h"

namespace sublist {

    static bool are_equal(
        const std::vector<int>& first,
        const std::vector<int>& second,
        size_t start) {

        for (size_t i = 0; i < first.size(); ++i) {
            if (first[i] != second[start + i]) {
                return false;
            }
        }
        return true;
    }

    static bool is_sublist(
        const std::vector<int>& first,
        const std::vector<int>& second) {

        if (first.size() > second.size()) {
            return false;
        }

        if (first.empty()) {
            return true;
        }

        for (size_t i = 0; i <= second.size() - first.size(); ++i) {
            if (are_equal(first, second, i)) {
                return true;
            }
        }

        return false;
    }

    List_comparison sublist(
        const std::vector<int>& first,
        const std::vector<int>& second) {

        if (first.size() == second.size() &&
            are_equal(first, second, 0)) {
            return List_comparison::equal;
            }

        if (is_sublist(first, second)) {
            return List_comparison::sublist;
        }

        if (is_sublist(second, first)) {
            return List_comparison::superlist;
        }

        return List_comparison::unequal;
    }

} // namespace sublist