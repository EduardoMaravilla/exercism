import random
import string

class Robot:
    used_names = []

    def __init__(self):
        self._name = self._generate_unique_name()

    @property
    def name(self):
        return self._name

    def reset(self):
        self._name = self._generate_unique_name()

    def _generate_name(self):
        letter_one = random.choice(string.ascii_uppercase)
        letter_two = random.choice(string.ascii_uppercase)
        number_code = str(random.randint(0, 999)).zfill(3)
        return f"{letter_one}{letter_two}{number_code}"

    def _generate_unique_name(self):
        while True:
            new_name = self._generate_name()
            if new_name not in Robot.used_names:
                Robot.used_names.append(new_name)
                return new_name

    @classmethod
    def release_names(cls):
        cls.used_names = []