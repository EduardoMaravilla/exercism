def _scale_chromatic(tonic, scale):
    tonic = tonic[0].upper() + tonic[1:].lower()
    index = scale.index(tonic)

    return scale[index:] + scale[:index]


class Scale:
    def __init__(self, tonic):
        self.scale_sharp = [
            "A", "A#", "B", "C", "C#", "D",
            "D#", "E", "F", "F#", "G", "G#"
        ]

        self.scale_flat = [
            "A", "Bb", "B", "C", "Db", "D",
            "Eb", "E", "F", "Gb", "G", "Ab"
        ]

        self.sharp = [
            "C", "a", "G", "D", "A", "E", "B", "F#",
            "e", "b", "f#", "c#", "g#", "d#"
        ]

        self.flat = [
            "F", "Bb", "Eb", "Ab", "Db", "Gb",
            "d", "g", "c", "f", "bb", "eb"
        ]

        self._step = {'m': 1, 'M': 2, 'A': 3}

        self.tonic = tonic
        self._result_scale = []

        if tonic in self.sharp:
            self._result_scale = _scale_chromatic(tonic, self.scale_sharp)
        elif tonic in self.flat:
            self._result_scale = _scale_chromatic(tonic, self.scale_flat)

    def chromatic(self):
        return self._result_scale

    def interval(self, intervals):
        result = [self._result_scale[0]]
        step = 0

        for i in intervals:
            step += self._step[i]
            if step > 11:
                step -= 12
            result.append(self._result_scale[step])

        return result

