class Zipper:

    def __init__(self,focus, path):
        self.focus = focus
        self.path = path

    @staticmethod
    def from_tree(tree):
        return Zipper(tree, [])

    def value(self):
        return self.focus['value']

    def set_value(self, value):
        new_focus = {**self.focus, 'value': value}
        return Zipper(new_focus, self.path)

    def left(self):
        if self.focus['left'] is None:
            return None
        new_path = self.path + [('left', self.focus['value'], self.focus['right'])]
        return Zipper(self.focus['left'], new_path)

    def set_left(self, tree):
        new_focus = {**self.focus, 'left': tree}
        return Zipper(new_focus, self.path)

    def right(self):
        if self.focus['right'] is None:
            return None
        new_path = self.path + [('right', self.focus['value'], self.focus['left'])]
        return Zipper(self.focus['right'], new_path)

    def set_right(self, tree):
        new_focus = {**self.focus, 'right': tree}
        return Zipper(new_focus, self.path)

    def up(self):
        if not self.path:
            return None

        direction, parent_value, other_child = self.path[-1]
        remaining_path = self.path[:-1]

        if direction == 'left':
            new_focus = {
                'value': parent_value,
                'left': self.focus,
                'right': other_child
            }
        else:
            new_focus = {
                'value': parent_value,
                'left': other_child,
                'right': self.focus
            }

        return Zipper(new_focus, remaining_path)

    def to_tree(self):
        current = self
        while current.path:
            current = current.up()
        return current.focus
