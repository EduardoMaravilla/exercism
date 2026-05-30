#include "high_scores.h"

#include <algorithm>

namespace arcade {

std::vector<int> HighScores::list_scores() {
    return scores;
}

int HighScores::latest_score() const {
    return scores.back();
}

int HighScores::personal_best() {
    return *std::max_element(scores.begin(),scores.end());
}

std::vector<int> HighScores::top_three() {
    std::vector<int> result = scores;
    std::sort(result.begin(),result.end());
    std::reverse(result.begin(),result.end());
    if (result.size() > 3)  result.resize(3);
    return result;
}

}  // namespace arcade
