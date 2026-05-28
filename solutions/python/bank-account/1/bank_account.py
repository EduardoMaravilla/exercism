class BankAccount:
    def __init__(self):
        self._balance = 0
        self._is_open = False

    def _ensure_open(self):
        if not self._is_open:
            raise ValueError("account not open")

    def get_balance(self):
        self._ensure_open()
        return self._balance

    def open(self):
        if self._is_open:
            raise ValueError("account already open")

        self._balance = 0
        self._is_open = True

    def deposit(self, amount):
        self._ensure_open()
        if amount <= 0:
            raise ValueError("amount must be greater than 0")
        self._balance += amount

    def withdraw(self, amount):
        self._ensure_open()
        if amount <= 0:
            raise ValueError("amount must be greater than 0")
        if amount > self._balance:
            raise ValueError("amount must be less than balance")
        self._balance -= amount

    def close(self):
        self._ensure_open()
        self._is_open = False
        self._balance = 0