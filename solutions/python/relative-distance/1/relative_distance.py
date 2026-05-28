from collections import deque

class RelativeDistance:
    def __init__(self, family_tree):
        self.family_tree = family_tree

    def degree_of_separation(self, person_a, person_b):
        neighbors = {}

        for parent, children in self.family_tree.items():
            if parent not in neighbors:
                neighbors[parent] = set()

            for child in children:
                if child not in neighbors:
                    neighbors[child] = set()

                neighbors[parent].add(child)
                neighbors[child].add(parent)

            for child_a in children:
                for child_b in children:
                    if child_a != child_b:
                        neighbors[child_a].add(child_b)
                        neighbors[child_b].add(child_a)

        if person_a not in neighbors:
            raise ValueError("Person A not in family tree.")

        if person_b not in neighbors:
            raise ValueError("Person B not in family tree.")

        queue = deque([[person_a, 0]])
        visited = {person_a}

        while queue:
            current, degree = queue.popleft()

            if current == person_b:
                return degree

            for neighbor in neighbors[current]:
                if neighbor not in visited:
                    visited.add(neighbor)
                    queue.append([neighbor, degree + 1])

        raise ValueError("No connection between person A and person B.")