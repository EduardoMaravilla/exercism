def append(list1, list2):
    return list1 + list2


def concat(lists):
    return [item for sublist in lists for item in sublist]


def filter(function, lst):
    return [x for x in lst if function(x)]


def length(lst):
    return len(lst)


def map(function, lst):
    return [function(x) for x in lst]


def foldl(function, lst, initial):
    acc = initial
    for el in lst:
        acc = function(acc, el)
    return acc


def foldr(function, lst, initial):
    acc = initial
    for el in reversed(lst):
        acc = function(acc, el)
    return acc


def reverse(lst):
    return lst[::-1]