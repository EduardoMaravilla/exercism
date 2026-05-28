import java.util.Arrays;

public class CircularBuffer<T> {

    private final Object[] buffer;
    private int head = 0;
    private int tail = 0;
    private int count = 0;

    public CircularBuffer(int capacity) {
        this.buffer = new Object[capacity];
    }

    public T read() throws BufferIOException {
        if (isEmpty()) {
            throw new BufferIOException("Tried to read from empty buffer");
        }

        @SuppressWarnings("unchecked")
        T value = (T) buffer[head];

        buffer[head] = null;
        head = (head + 1) % buffer.length;
        count--;

        return value;
    }

    public void write(T data) throws BufferIOException {
        if (isFull()) {
            throw new BufferIOException("Tried to write to full buffer");
        }

        buffer[tail] = data;
        tail = (tail + 1) % buffer.length;
        count++;
    }

    public void overwrite(T data) {
        if (isFull()) {
            buffer[head] = data;
            head = (head + 1) % buffer.length;
            tail = (tail + 1) % buffer.length; 
        } else {
            try {
                write(data);
            } catch (BufferIOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void clear() {
        Arrays.fill(buffer, null);
        head = 0;
        tail = 0;
        count = 0;
    }

    private boolean isEmpty() {
        return count == 0;
    }

    private boolean isFull() {
        return count == buffer.length;
    }
}