class BowlingGame:
    def __init__(self):
        self._rolls = []
        self._has_bonus_roll = True
        self._frames = 0
        self._points = 0
        self._square = 0
        self._bonus = 0
        self._strike = False

    def roll(self, pins):
        if pins < 0:
            raise Exception("Negative roll is invalid")
        if pins > 10:
            raise Exception("Pin count exceeds pins on the lane")

        if self._frames == 10:
            if not self._has_bonus_roll:
                raise Exception("Cannot roll after game is over")

            self._bonus += 1

            if (not self._strike and self._bonus > 1) or (self._strike and self._bonus > 2):
                raise Exception("Cannot roll after game is over")

            if self._strike:
                if self._bonus == 2 and self._points < 10:
                    if self._points + pins > 10:
                        raise Exception("Pin count exceeds pins on the lane")

                if self._bonus == 1:
                    self._points = pins
            else:
                pass

            self._rolls.append(pins)
            return

        self._rolls.append(pins)
        if pins == 10 and self._square == 0:
            self._strike = True
            self._frames += 1
            if self._frames == 10:
                self._has_bonus_roll = True
        else:
            self._points += pins
            self._square += 1

            if self._points > 10:
                raise Exception("Pin count exceeds pins on the lane")

            if self._square == 2:
                self._frames += 1
                if self._frames == 10:
                    if self._points == 10:
                        self._has_bonus_roll = True
                        self._strike = False
                    else:
                        self._has_bonus_roll = False

                self._points = 0
                self._square = 0

    def score(self):
        is_incomplete = (
                self._frames < 10 or
                (self._has_bonus_roll and self._bonus == 0 and not self._strike) or
                (self._has_bonus_roll and self._bonus < 2 and self._strike)
        )
        if is_incomplete:
            raise Exception("Score cannot be taken until the end of the game")

        total_score = 0
        k = 0
        squares = 10

        while squares > 0:
            if squares == 1:
                total_score += sum(self._rolls[k:])
                break
            if self._rolls[k] == 10:
                total_score += self._rolls[k] + self._rolls[k + 1] + self._rolls[k + 2]
                k += 1
            elif self._rolls[k] + self._rolls[k + 1] == 10:
                total_score += self._rolls[k] + self._rolls[k + 1] + self._rolls[k + 2]
                k += 2
            else:
                total_score += self._rolls[k] + self._rolls[k + 1]
                k += 2

            squares -= 1

        return total_score