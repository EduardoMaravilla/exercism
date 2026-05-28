class BufferFullException(BufferError):
    def __init__(self, message):
        super().__init__(message)


class BufferEmptyException(BufferError):
    def __init__(self, message):
        super().__init__(message)


from collections import deque

class CircularBuffer:
    def __init__(self, capacity):
        self.buffer = deque(maxlen=capacity)

    def write(self, data):
        if len(self.buffer) == self.buffer.maxlen:
            raise BufferFullException("Circular buffer is full")
        self.buffer.append(data)

    def read(self):
        if not self.buffer:
            raise BufferEmptyException("Circular buffer is empty")
        return self.buffer.popleft()

    def overwrite(self, data):
        self.buffer.append(data)

    def clear(self):
        self.buffer.clear()