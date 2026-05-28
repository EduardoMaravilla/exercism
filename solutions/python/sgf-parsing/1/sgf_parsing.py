class SgfTree:
    def __init__(self, properties=None, children=None):
        self.properties = properties or {}
        self.children = children or []

    def __eq__(self, other):
        if not isinstance(other, SgfTree):
            return False
        for key, value in self.properties.items():
            if key not in other.properties:
                return False
            if other.properties[key] != value:
                return False
        for key in other.properties.keys():
            if key not in self.properties:
                return False
        if len(self.children) != len(other.children):
            return False
        for child, other_child in zip(self.children, other.children):
            if child != other_child:
                return False
        return True

    def __ne__(self, other):
        return not self == other


def parse(input_string):
    if not input_string:
        raise ValueError("tree missing")
    if input_string == "()":
        raise ValueError("tree with no nodes")
    if input_string[0] != "(" or input_string[-1] != ")":
        raise ValueError("tree missing")

    index = [1]

    def parse_tree():
        if index[0] >= len(input_string) or input_string[index[0]] != ';':
            raise ValueError("tree missing")

        return parse_nodes()

    def parse_nodes():
        if input_string[index[0]] != ';':
            raise ValueError("tree missing")
        index[0] += 1

        properties = {}
        while index[0] < len(input_string):
            char = input_string[index[0]]

            if char.isupper():
                prop_key = ""

                while index[0] < len(input_string) and input_string[index[0]].isalpha():
                    prop_key += input_string[index[0]]
                    index[0] += 1

                if not prop_key.isupper():
                    raise ValueError("property must be in uppercase")

                if index[0] >= len(input_string) or input_string[index[0]] != '[':
                    raise ValueError("properties without delimiter")

                values = []
                while index[0] < len(input_string) and input_string[index[0]] == '[':
                    index[0] += 1
                    val = ""
                    escaped = False
                    while index[0] < len(input_string):
                        c = input_string[index[0]]
                        if escaped:
                            if c == '\n':
                                pass
                            elif c in ['\t', ' ']:
                                val += ' '
                            else:
                                val += c
                            escaped = False
                        elif c == '\\':
                            escaped = True
                        elif c == ']':
                            index[0] += 1
                            break
                        else:
                            val += ' ' if c == '\t' else c
                        index[0] += 1
                    values.append(val)
                properties[prop_key] = values

            elif char == ';' or char == '(' or char == ')':
                break
            elif char.islower():
                raise ValueError("property must be in uppercase")
            else:
                index[0] += 1

        children = []
        if index[0] < len(input_string) and input_string[index[0]] == ';':
            children.append(parse_nodes())
        while index[0] < len(input_string) and input_string[index[0]] == '(':
            index[0] += 1
            children.append(parse_nodes())
            if input_string[index[0]] != ')':
                raise ValueError("tree missing")
            index[0] += 1

        return SgfTree(properties, children)

    result = parse_tree()

    if index[0] < len(input_string) - 1:
        index[0] += 1

    return result