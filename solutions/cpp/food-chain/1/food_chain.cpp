#include "food_chain.h"

#include <vector>

namespace food_chain {
    static std::vector<std::string> animal = {
        "fly.\n", "spider.\n", "bird.\n", "cat.\n", "dog.\n", "goat.\n", "cow.\n", "horse.\n"
    };
    static std::vector<std::string> desAnimal = {
        "It wriggled and jiggled and tickled inside her.\n", "How absurd to swallow a bird!\n",
        "Imagine that, to swallow a cat!\n", "What a hog, to swallow a dog!\n",
        "Just opened her throat and swallowed a goat!\n", "I don't know how she swallowed a cow!\n"
    };
    static std::vector<std::string> chainFood = {
        "She swallowed the spider to catch the fly.\n",
        "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.\n",
        "She swallowed the cat to catch the bird.\n", "She swallowed the dog to catch the cat.\n",
        "She swallowed the goat to catch the dog.\n", "She swallowed the cow to catch the goat.\n"
    };

    std::string verse(const int start) {
        std::string result;
        const std::string verse1 = "I don't know why she swallowed the fly. Perhaps she'll die.\n";
        result.append("I know an old lady who swallowed a ");
        result.append(animal[start - 1]);
        if ( start == 1) {
            result.append(verse1);
            return result;
        }
        if (start == 8) {
            result.append("She's dead, of course!\n");
            return result;
        }
        result.append(desAnimal[start - 2]);
        for (int i = start - 2 ; i >= 0; i--) {
            result.append(chainFood[i]);
        }
        result.append(verse1);
        return result;
    }

    std::string verses(const int start, const int end) {
        std::string result;
        for (int i = start; i <= end; i++) {
            result.append(verse(i));
            result.append("\n");
        }
        return result;
    }

    std::string sing() {
        return verses(1,8);
    }
} // namespace food_chain
