#include "parallel_letter_frequency.h"

#include <array>
#include <cctype>
#include <mutex>
#include <thread>
#include <vector>

namespace parallel_letter_frequency {
    void process_range(std::array<int, 26> &local_freq, const std::vector<std::string_view> &texts,
                       const std::size_t begin, const std::size_t end
    ) {
        for (std::size_t i = begin; i < end; ++i) {
            for (const char letter: texts[i]) {
                const auto c = static_cast<unsigned char>(letter);
                if (std::isalpha(c)) {
                    ++local_freq[std::tolower(c) - 'a'];
                }
            }
        }
    }

    std::unordered_map<char, int> frequency(const std::vector<std::string_view> &texts) {
        if (texts.empty()) {
            return {};
        }
        std::unordered_map<char, int> frequencies;
        std::mutex mtx;

        unsigned int thread_count = std::thread::hardware_concurrency();

        if (thread_count == 0) {
            thread_count = 1;
        }
        thread_count = std::min(
            thread_count,
            static_cast<unsigned int>(texts.size())
        );

        std::vector<std::thread> workers;
        workers.reserve(thread_count);

        const std::size_t chunk_size = (texts.size() + thread_count - 1) / thread_count;

        for (unsigned int t = 0; t < thread_count; ++t) {
            const std::size_t begin = t * chunk_size;
            const std::size_t end = std::min(begin + chunk_size, texts.size());

            if (begin >= texts.size()) {
                break;
            }

            workers.emplace_back(
                [&frequencies, &texts, &mtx, begin, end]() {
                    std::array<int, 26> local_freq{};
                    process_range(local_freq, texts, begin, end);
                    std::lock_guard<std::mutex> lock(mtx);
                    for (std::size_t i = 0; i < 26; ++i) {
                        if (local_freq[i] > 0) {
                            frequencies['a' + static_cast<char>(i)] +=
                                    local_freq[i];
                        }
                    }
                }
            );
        }

        for (auto &worker: workers) {
            worker.join();
        }

        return frequencies;
    }
}
