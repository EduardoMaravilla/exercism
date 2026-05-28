def flatten(iterable):
    result = []
    for element in iterable:
        if isinstance(element, list):
            result.extend(flatten(element))
        elif element is not None:
            result.append(element)

    return result