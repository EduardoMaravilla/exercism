def egg_count(display_value):
    eggs = 0
    while display_value != 0:
        eggs += display_value % 2
        display_value //=2
    return eggs