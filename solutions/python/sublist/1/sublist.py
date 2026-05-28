"""
This exercise stub and the test suite contain several enumerated constants.

Enumerated constants can be done with a NAME assigned to an arbitrary,
but unique value. An integer is traditionally used because it’s memory
efficient.
It is a common practice to export both constants and functions that work with
those constants (ex. the constants in the os, subprocess and re modules).

You can learn more here: https://en.wikipedia.org/wiki/Enumerated_type
"""

# sublist.py
SUBLIST = 0
SUPERLIST = 1
EQUAL = 2
UNEQUAL = 3

def sublist(list_one, list_two):
    if list_one == list_two:
        return EQUAL
    elif list_one in [list_two[i:i+len(list_one)] for i in range(len(list_two) - len(list_one) + 1)]:
        return SUBLIST
    elif list_two in [list_one[i:i+len(list_two)] for i in range(len(list_one) - len(list_two) + 1)]:
        return SUPERLIST
    else:
        return UNEQUAL

