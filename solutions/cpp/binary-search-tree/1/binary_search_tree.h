#pragma once
#include <memory>
#include <stack>
#include <vector>

namespace binary_search_tree {
    template<typename T>
    class binary_tree {
        T data_;
        std::unique_ptr<binary_tree> left_;
        std::unique_ptr<binary_tree> right_;

        void inorder(std::vector<T> &values) const {
            if (left_) {
                left_->inorder(values);
            }
            values.push_back(data_);

            if (right_) {
                right_->inorder(values);
            }
        }

    public:
        explicit binary_tree(T data) : data_(data) {
        }

        void insert(T value) {
            if (value <= data_) {
                if (!left_) {
                    left_ = std::make_unique<binary_tree>(value);
                } else {
                    left_->insert(value);
                }
            } else {
                if (!right_) {
                    right_ = std::make_unique<binary_tree>(value);
                } else {
                    right_->insert(value);
                }
            }
        }

        const T &data() const {
            return data_;
        }

        const std::unique_ptr<binary_tree> &left() const {
            return left_;
        }

        const std::unique_ptr<binary_tree> &right() const {
            return right_;
        }

        class iterator {
            std::stack<const binary_tree<T> *> nodes_;

            void push_left(const binary_tree<T> *node) {
                while (node) {
                    nodes_.push(node);
                    node = node->left_.get();
                }
            }

        public:
            explicit iterator(const binary_tree<T> *root) {
                push_left(root);
            }

            iterator() = default;

            T &operator*() const {
                return const_cast<T &>(nodes_.top()->data_);
            }

            iterator &operator++() {
                const binary_tree<T> *node = nodes_.top();
                nodes_.pop();
                push_left(node->right_.get());
                return *this;
            }

            bool operator!=(const iterator &other) const {
                if (nodes_.empty() && other.nodes_.empty()) return false;
                if (nodes_.empty() != other.nodes_.empty()) return true;
                return nodes_.top() != other.nodes_.top();
            }
        };

        iterator begin() const {
            return iterator(this);
        }

        iterator end() const {
            return iterator(nullptr);
        }
    };
} // namespace binary_search_tree
