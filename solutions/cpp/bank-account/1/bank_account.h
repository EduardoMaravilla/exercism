#pragma once
#include <string>
#include <mutex>

namespace Bankaccount {
class Bankaccount {
    std::string name;
    int balance_;
    mutable std::mutex mtx;
public:
    Bankaccount();
    void open();
    [[nodiscard]] int balance() const;

    void deposit(int deposit);

    void withdraw(int debt);

    void close();

};  // class Bankaccount

}  // namespace Bankaccount
