# Game status categories
STATUS_WIN = 'win'
STATUS_LOSE = 'lose'
STATUS_ONGOING = 'ongoing'


class Hangman:
    def __init__(self, word):
        self.remaining_guesses = 9
        self.status = STATUS_ONGOING
        self.word = word
        self.guesses = set()

    def guess(self, char):
        if self.status != STATUS_ONGOING:
            raise ValueError("The game has already ended.")

        if char in self.word and char not in self.guesses:
            self.guesses.add(char)
        else:
            self.remaining_guesses -= 1

        self._update_status()

    def _update_status(self):
        if all(letter in self.guesses for letter in self.word):
            self.status = STATUS_WIN

        elif self.remaining_guesses < 0:
            self.status = STATUS_LOSE

    def get_masked_word(self):
        return "".join([char if char in self.guesses else "_" for char in self.word])

    def get_status(self):
        return self.status