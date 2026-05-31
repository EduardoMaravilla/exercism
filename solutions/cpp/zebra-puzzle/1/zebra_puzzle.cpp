#include "zebra_puzzle.h"

#include <array>
#include <vector>

namespace zebra_puzzle {
    struct state {
        std::array<int, 5> color{};
        std::array<int, 5> nat{};
        std::array<int, 5> drink{};
        std::array<int, 5> pet{};
        std::array<int, 5> hobby{};
        state() {
            color.fill(-1);
            nat.fill(-1);
            drink.fill(-1);
            pet.fill(-1);
            hobby.fill(-1);
        }
    };

    static bool used(const int arr[5], const int value) {
        for (int i = 0; i < 5; i++) {
            if (arr[i] == value) {
                return true;
            }
        }
        return false;
    }

    static int pos(const int arr[5], const int value) {
        for (int i = 0; i < 5; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

    static bool valid(state *s) {
        int p;
        /* 1. The person in the middle house drinks milk */
        if (s->drink[2] != MILK && used(s->drink.data(), MILK))
            return false;
        /* 2. Norwegian lives in first house */
        if (s->nat[0] != NORWEGIAN && used(s->nat.data(), NORWEGIAN))
            return false;
        /* 3. Englishman lives in red house */
        p = pos(s->nat.data(), ENGLISHMAN);
        if (p != -1 && s->color[p] != -1 && s->color[p] != RED)
            return false;
        /* 4. Spaniard owns dog */
        p = pos(s->nat.data(), SPANIARD);
        if (p != -1 && s->pet[p] != -1 && s->pet[p] != DOG)
            return false;
        /* 5. Green drinks coffee */
        p = pos(s->color.data(), GREEN);
        if (p != -1 && s->drink[p] != -1 && s->drink[p] != COFFEE)
            return false;
        /* 6. Ukrainian drinks tea */
        p = pos(s->nat.data(), UKRAINIAN);
        if (p != -1 && s->drink[p] != -1 && s->drink[p] != TEA)
            return false;
        /* 7. Green is right of ivory */
        const int g = pos(s->color.data(), GREEN);
        const int i = pos(s->color.data(), IVORY);
        if (g != -1 && i != -1 && g != i + 1)
            return false;
        /* 8. Yellow house is painter */
        p = pos(s->color.data(), YELLOW);
        if (p != -1 && s->hobby[p] != -1 && s->hobby[p] != PAINTING)
            return false;
        /* 9. Snail owner dances */
        p = pos(s->pet.data(), SNAILS);
        if (p != -1 && s->hobby[p] != -1 && s->hobby[p] != DANCING)
            return false;
        /* 10. Reading next to fox */
        const int r = pos(s->hobby.data(), READING);
        const int f = pos(s->pet.data(), FOX);
        if (r != -1 && f != -1 && abs(r - f) != 1)
            return false;
        /* 11. Painter next to horse */
        int pa = pos(s->hobby.data(), PAINTING);
        int h = pos(s->pet.data(), HORSE);
        if (pa != -1 && h != -1 && abs(pa - h) != 1)
            return false;
        /* 12. Football drinks orange juice */
        p = pos(s->hobby.data(), FOOTBALL);
        if (p != -1 && s->drink[p] != -1 && s->drink[p] != ORANGE_JUICE)
            return false;
        /* 13. Japanese plays chess */
        p = pos(s->nat.data(), JAPANESE);
        if (p != -1 && s->hobby[p] != -1 && s->hobby[p] != CHESS)
            return false;
        /* 14. Norwegian next to blue */
        const int n = pos(s->nat.data(), NORWEGIAN);
        const int b = pos(s->color.data(), BLUE);
        if (n != -1 && b != -1 && abs(n - b) != 1)
            return false;

        return true;
    }

    static bool backtrack(state *s, int i) {
        if (i == 5) {
            return true;
        }
        for (int c = 0; c < 5; c++) {
            if (used(s->color.data(), c)) continue;
            s->color[i] = c;
            for (int n = 0; n < 5; n++) {
                if (used(s->nat.data(), n)) continue;
                s->nat[i] = n;
                for (int d = 0; d < 5; d++) {
                    if (used(s->drink.data(), d)) continue;
                    s->drink[i] = d;
                    for (int p = 0; p < 5; p++) {
                        if (used(s->pet.data(), p)) continue;
                        s->pet[i] = p;
                        for (int h = 0; h < 5; h++) {
                            if (used(s->hobby.data(), h)) continue;
                            s->hobby[i] = h;
                            if (valid(s)) {
                                if (backtrack(s, i + 1)) {
                                    return true;
                                }
                            }
                            s->hobby[i] = -1;
                        }
                        s->pet[i] = -1;
                    }
                    s->drink[i] = -1;
                }
                s->nat[i] = -1;
            }
            s->color[i] = -1;
        }
        return false;
    }

    Solution solve() {
        state s;
        Solution solution;

        backtrack(&s, 0);
        const int water_house = pos(s.drink.data(),WATER);
        const int owns_zebra = pos(s.pet.data(),ZEBRA);
        const int nat_water_house = s.nat[water_house];
        const int nat_owns_zebra = s.nat[owns_zebra];
        const std::vector<std::string> nat_string ={"Englishman","Spaniard","Ukrainian","Norwegian","Japanese"};
        solution.drinksWater = nat_string[nat_water_house];
        solution.ownsZebra = nat_string[nat_owns_zebra];

        return solution;
    }
} // namespace zebra_puzzle
