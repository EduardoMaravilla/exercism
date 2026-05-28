from random import randint


class Character:
    def __init__(self):
        self._strength = self.ability()
        self._dexterity = self.ability()
        self._constitution = self.ability()
        self._intelligence = self.ability()
        self._wisdom = self.ability()
        self._charisma = self.ability()
        self.hitpoints = 10 + modifier(self.constitution)

    def ability(self):
        return roll_ability()

    @property
    def strength(self):
        return self._strength

    @property
    def dexterity(self):
        return self._dexterity

    @property
    def constitution(self):
        return self._constitution

    @property
    def intelligence(self):
        return self._intelligence

    @property
    def wisdom(self):
        return self._wisdom

    @property
    def charisma(self):
        return self._charisma


def modifier(value):
    return (value - 10) // 2


def roll_ability():
    dices = [randint(1, 6) for _ in range(4)]
    return sum(dices) - min(dices)