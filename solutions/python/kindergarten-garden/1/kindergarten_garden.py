PLANT_MAP = {
    'V': 'Violets',
    'R': 'Radishes',
    'C': 'Clover',
    'G': 'Grass'
}


class Garden:
    DEFAULT_STUDENTS = [
        'Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred',
        'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry'
    ]

    def __init__(self, diagram, students=None):
        self.students = sorted(students or self.DEFAULT_STUDENTS)
        self.diagram = diagram.splitlines()

        self._student_index = {
            student: i for i, student in enumerate(self.students)
        }

    def plants(self, student):
        i = self._student_index[student] * 2

        return [
            PLANT_MAP[self.diagram[0][i]],
            PLANT_MAP[self.diagram[0][i + 1]],
            PLANT_MAP[self.diagram[1][i]],
            PLANT_MAP[self.diagram[1][i + 1]],
        ]