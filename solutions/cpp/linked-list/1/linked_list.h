#pragma once
#include <cstddef>

namespace linked_list {
    template<typename T>
    class Element {
    public:
        T data;
        Element *prev;
        Element *next;

        explicit Element(T data, Element *prev = nullptr, Element *next = nullptr)
            : data(data), prev(prev), next(next) {
        }
    };

    template<typename T>
    class List {
        Element<T> *head = nullptr;
        Element<T> *tail = nullptr;

    public:
        void push(T value) {
            auto *node = new Element<T>(value, tail, nullptr);

            if (tail)
                tail->next = node;
            else
                head = node;

            tail = node;
        }

        T pop() {
            T value = tail->data;

            auto *old = tail;
            tail = tail->prev;

            if (tail)
                tail->next = nullptr;
            else
                head = nullptr;

            delete old;

            return value;
        }

        T shift() {
            T value = head->data;
            auto *old = head;
            head = head->next;
            if (head)
                head->prev = nullptr;
            else
                tail = nullptr;
            delete old;
            return value;
        }

        void unshift(T value) {
            auto *node = new Element<T>(value, nullptr, head);
            if (head)
                head->prev = node;
            else
                tail = node;
            head = node;
        }

        size_t count() {
            size_t count = 0;
            Element<T> *current = head;
            while (current) {
                count++;
                current = current->next;
            }
            return count;
        }

        void erase(T value) {
            Element<T>* current = head;

            while (current) {
                if (current->data == value) {
                    if (current->prev)
                        current->prev->next = current->next;
                    else
                        head = current->next;

                    if (current->next)
                        current->next->prev = current->prev;
                    else
                        tail = current->prev;

                    delete current;
                    return;
                }
                current = current->next;
            }
        }
    };
} // namespace linked_list
