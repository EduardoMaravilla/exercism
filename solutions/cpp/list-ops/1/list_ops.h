#pragma once

#include <algorithm>
#include <vector>

namespace list_ops {
    template<typename T>
    void append(std::vector<T> &left, std::vector<T> &right) {
        left.insert(left.end(), right.begin(), right.end());
    }

    template<typename T>
    std::vector<T> concat(const std::vector<std::vector<T> > &input) {
        std::vector<T> result;

        for (const auto &inner: input) {
            result.insert(result.end(),
                          inner.begin(),
                          inner.end());
        }
        return result;
    }

    template<typename T, typename Predicate>
    std::vector<T> filter(const std::vector<T> &input, Predicate pred) {
        std::vector<T> result;
        result.reserve(input.size());

        for (const auto &value: input) {
            if (pred(value)) {
                result.push_back(value);
            }
        }
        return result;
    }

    template<typename T>
    size_t length(std::vector<T> &input) {
        return input.size();
    }

    template<typename T, typename Func>
    auto map(const std::vector<T> &input, Func func) {
        using U = decltype(func(std::declval<T>()));

        std::vector<U> result;

        for (const auto &value: input) {
            result.push_back(func(value));
        }

        return result;
    }

    template<typename T, typename Func>
    T foldl(std::vector<T> &input, T init, Func func) {
        T acc = init;
        for (const auto &value: input) {
            acc = func(acc, value);
        }
        return acc;
    }

    template<typename T, typename Func>
    T foldr(const std::vector<T> &input, T init, Func func) {
        T acc = init;

        for (size_t i = input.size(); i > 0; --i) {
            acc = func(acc, input[i - 1]);
        }

        return acc;
    }

    template<typename T>
    std::vector<T> reverse(std::vector<T> &input) {
        std::vector<T> result = input;
        std::reverse(result.begin(), result.end());
        return result;
    }
} // namespace list_ops
