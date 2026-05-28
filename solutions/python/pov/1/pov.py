from json import dumps


class Tree:
    def __init__(self, label, children=None):
        self.label = label
        self.children = children if children is not None else []

    def __dict__(self):
        return {self.label: [c.__dict__() for c in sorted(self.children)]}

    def __str__(self, indent=None):
        return dumps(self.__dict__(), indent=indent)

    def __lt__(self, other):
        return self.label < other.label

    def __eq__(self, other):
        return self.__dict__() == other.__dict__()

    def _get_path(self, target, path):
        """Método auxiliar para encontrar el camino de la raíz a un nodo."""
        path.append(self)
        if self.label == target:
            return path
        for child in self.children:
            result = child._get_path(target, path[:])
            if result:
                return result
        return None

    def from_pov(self, from_node):
        path = self._get_path(from_node, [])
        if not path:
            raise ValueError("Tree could not be reoriented")

        nodes = path[::-1]
        new_root = nodes[0]


        for i in range(len(nodes) - 1):
            current = nodes[i]
            parent = nodes[i + 1]

            parent.children = [c for c in parent.children if c.label != current.label]
            current.children.append(parent)

        return new_root

    def path_to(self, from_node, to_node):

        new_tree = self.from_pov(from_node)

        full_path = new_tree._get_path(to_node, [])

        if not full_path:
            raise ValueError("No path found")

        return [node.label for node in full_path]