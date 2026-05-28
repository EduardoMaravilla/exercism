class HighScores:
    def __init__(self, scores):
        self._scores = scores

    @property
    def scores(self):
        return self._scores

    def latest(self):
        return self._scores[-1]

    def personal_best(self):
        return max(self._scores)

    def personal_top_three(self):
        return sorted(self._scores, reverse=True)[:3]