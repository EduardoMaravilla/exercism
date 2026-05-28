class Node:
    def __init__(self, value, succeeding=None, previous=None):
        self.value = value
        self.succeeding = succeeding
        self.previous = previous


class LinkedList:
    def __init__(self):
        self.head = None
        self.tail = None
        self._size = 0

    def push(self, value):
        new_node = Node(value)
        if not self.head:
            self.head = self.tail = new_node
        else:
            self.tail.succeeding = new_node
            new_node.previous = self.tail
            self.tail = new_node
        self._size += 1

    def pop(self):
        if self.tail is None:
            raise IndexError("List is empty")
        removed_node = self.tail
        value = removed_node.value
        if self.head == self.tail:
            self.head = self.tail = None
        else:
            self.tail = self.tail.previous
            self.tail.succeeding = None

        removed_node.previous = None

        self._size -= 1
        return value

    def shift(self):
        if self.head is None:
            raise IndexError("List is empty")
        removed_node = self.head

        if self.head.succeeding is not None:
            self.head = self.head.succeeding
            self.head.previous = None
        else:
            self.head = self.tail = None
        removed_node.succeeding = None
        self._size -= 1
        return removed_node.value

    def unshift(self, value):
        new_node = Node(value)
        if self.head is None:
            self.head = self.tail = new_node
        else:
            new_node.succeeding = self.head
            self.head.previous = new_node
            self.head = new_node
        self._size += 1

    def delete(self, value):
        if self.head is None:
            raise ValueError("Value not found")

        current_node = self.head
        while current_node is not None:
            if current_node.value == value:
                if current_node.previous is not None:
                    current_node.previous.succeeding = current_node.succeeding
                else:
                    self.head = current_node.succeeding

                if current_node.succeeding is not None:
                    current_node.succeeding.previous = current_node.previous
                else:
                    self.tail = current_node.previous

                current_node.previous = None
                current_node.succeeding = None

                self._size -= 1
                return

            current_node = current_node.succeeding

        raise ValueError("Value not found")



    def __len__(self):
        return self._size

    def __iter__(self):
        current = self.head
        while current:
            yield current.value
            current = current.succeeding

