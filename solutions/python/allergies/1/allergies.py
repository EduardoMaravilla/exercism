ALLERGEN = {"cats": 128,
            "pollen": 64,
            "chocolate": 32,
            "tomatoes": 16,
            "strawberries": 8,
            "shellfish": 4,
            "peanuts": 2,
            "eggs": 1}


class Allergies:

    def __init__(self, score):
        self._allergens = []
        for key, value in ALLERGEN.items():
            if score & value:
                self._allergens.append(key)

    def allergic_to(self, item):
        return item in self._allergens

    @property
    def lst(self):
        return self._allergens
