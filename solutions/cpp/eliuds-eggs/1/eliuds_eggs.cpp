#include "eliuds_eggs.h"

namespace chicken_coop {

    int positions_to_quantity(int n) {
        int eggs = 0;
        while (n != 0) {
            eggs += n & 1;
            n >>= 1;
        }        
        return eggs;
    }
}  // namespace chicken_coop
