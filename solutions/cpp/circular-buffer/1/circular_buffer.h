#pragma once

#include <stdexcept>
#include <vector>

namespace circular_buffer {

    template <typename T>
    class circular_buffer {
        std::vector<T> buffer;
        size_t capacity_;
        size_t head_ = 0;
        size_t size_ = 0;

    public:
        explicit circular_buffer(size_t capacity)
            : buffer(capacity),
              capacity_(capacity) {}

        T read() {
            if (size_ == 0) {
                throw std::domain_error("Buffer is empty");
            }

            T value = buffer[head_];

            head_ = (head_ + 1) % capacity_;
            --size_;

            return value;
        }

        void write(T value) {
            if (size_ == capacity_) {
                throw std::domain_error("Buffer is full");
            }

            buffer[(head_ + size_) % capacity_] =
                std::move(value);

            ++size_;
        }

        void overwrite(T value) {
            if (size_ == capacity_) {
                head_ = (head_ + 1) % capacity_;
                --size_;
            }

            write(std::move(value));
        }

        void clear() {
            head_ = 0;
            size_ = 0;
        }
    };

} // namespace circular_buffer