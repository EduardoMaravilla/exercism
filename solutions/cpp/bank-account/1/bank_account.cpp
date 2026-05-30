#include "bank_account.h"

#include <stdexcept>

namespace Bankaccount {
    Bankaccount::Bankaccount() : balance_(0) {
    }

    void Bankaccount::open() {
        std::lock_guard<std::mutex> lock(mtx);
        if (!name.empty()) {
            throw std::runtime_error("Account already open");
        }
        name.append(std::to_string(rand() % 10001));
    }

    int Bankaccount::balance() const {
        std::lock_guard<std::mutex> lock(mtx);
        if (name.empty()) {
            throw std::runtime_error("Account not open");
        }
        return balance_;
    }

    void Bankaccount::deposit(const int deposit) {
        std::lock_guard<std::mutex> lock(mtx);
        if ( deposit < 1 || name.empty()) {
            throw std::runtime_error("Deposit must be greater than 0.");
        }
        balance_ += deposit;
    }

    void Bankaccount::withdraw(const int debt) {
        std::lock_guard<std::mutex> lock(mtx);
        if (debt < 1 || name.empty() || debt > balance_) {
            throw std::runtime_error("error");
        }
        balance_ -= debt;
    }

    void Bankaccount::close() {
        std::lock_guard<std::mutex> lock(mtx);
        if (name.empty()) {
            throw std::runtime_error("Account not open");
        }
        name="";
        balance_ = 0;
    }
}
