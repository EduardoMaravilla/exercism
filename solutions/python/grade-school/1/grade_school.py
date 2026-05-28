class School:
    def __init__(self):
        self._students = {}
        self._approved_students = []

    def add_student(self, name, grade):
        if name not in self._students:
            self._students[name] = grade
            self._approved_students.append(True)
        else:
            self._approved_students.append(False)

    def roster(self):
        return [
            name
            for name, _ in sorted(self._students.items(), key=lambda x: (x[1], x[0]))
        ]

    def grade(self, grade_number):
        results = []
        for key, value in self._students.items():
            if value == grade_number:
                results.append(key)
        return sorted(results)


    def added(self):
        return self._approved_students
