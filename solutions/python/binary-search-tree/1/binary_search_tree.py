class TreeNode:
    def __init__(self, data, left=None, right=None):
        self.data = data
        self.left = left
        self.right = right

    def __str__(self):
        return f'TreeNode(data={self.data}, left={self.left}, right={self.right})'

    def insert(self, data):
        if data <= self.data:
            if self.left:
                self.left.insert(data)
            else:
                self.left = TreeNode(data)
        else:
            if self.right:
                self.right.insert(data)
            else:
                self.right = TreeNode(data)


class BinarySearchTree:
    def __init__(self, tree_data):
        self._root = None
        for value in tree_data:
            self.insert(value)

    def insert(self, value):
        if self._root is None:
            self._root = TreeNode(value)
        else:
            self._root.insert(value)

    def data(self):
        return self._root

    def sorted_data(self):
        res = []

        def traverse(node):
            if node:
                traverse(node.left)
                res.append(node.data)
                traverse(node.right)

        traverse(self._root)
        return res