#include "simple_linked_list.h"

#include <stdexcept>

namespace simple_linked_list {
    std::size_t List::size() const {
        return current_size;
    }

    void List::push(const int entry) {
        auto* element = new Element{entry};
        element->next = head;
        head = element;
        ++current_size;
    }

    int List::pop() {
        const int value = head->data;
        auto *temp = head;
        head = temp->next;
        delete temp;
        current_size--;
        return value;
    }

    void List::reverse() {
        Element *prev = nullptr;
        Element *current = head;
        Element *next = nullptr;
        while (current) {
            next = current->next;
            current->next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    List::~List() {
        while (head) {
            auto* temp = head;
            head = head->next;
            delete temp;
        }
    }
} // namespace simple_linked_list
