class BufferFullException(BufferError):
    def __init__(self, message):
        super().__init__(message)


class BufferEmptyException(BufferError):
    def __init__(self, message):
        super().__init__(message)


class CircularBuffer:
    def __init__(self, capacity):
        self._capacity = capacity
        self._init_point = 0 
        self._elements_written = 0  
        self._values = [None] * capacity

    def read(self):
        if self._elements_written == 0:
            raise BufferEmptyException("Circular buffer is empty")

        value = self._values[self._init_point]
        self._init_point = (self._init_point + 1) % self._capacity
        self._elements_written -= 1
        return value

    def write(self, data):
        if self._elements_written == self._capacity:
            raise BufferFullException("Circular buffer is full")

        write_index = (self._init_point + self._elements_written) % self._capacity
        self._values[write_index] = data
        self._elements_written += 1

    def overwrite(self, data):
        if self._elements_written == self._capacity:
            self._values[self._init_point] = data
            self._init_point = (self._init_point + 1) % self._capacity
        else:
            self.write(data)

    def clear(self):
        self._values = [None] * self._capacity
        self._init_point = 0
        self._elements_written = 0