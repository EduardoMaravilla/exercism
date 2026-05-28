class InputCell:
    def __init__(self, initial_value):
        self._value = initial_value
        self.observers = []

    @property
    def value(self):
        return self._value

    @value.setter
    def value(self, new_value):
        if self._value != new_value:
            self._value = new_value
            affected_cells = []
            self._get_all_affected(affected_cells)
            old_values = {cell: cell.value for cell in affected_cells}

            for observer in self.observers:
                observer.update()

            for cell in affected_cells:
                if cell.value != old_values[cell]:
                    cell.fire_callbacks()

    def _get_all_affected(self, cells):
        for obs in self.observers:
            if obs not in cells:
                cells.append(obs)
                obs._get_all_affected(cells)


class ComputeCell:
    def __init__(self, inputs, compute_function):
        self.inputs = inputs
        self.func = compute_function
        self.callbacks = set()
        self.observers = []

        for i in inputs:
            i.observers.append(self)

        self._value = self.func([i.value for i in self.inputs])

    @property
    def value(self):
        return self._value

    def update(self):
        self._value = self.func([i.value for i in self.inputs])
        for obs in self.observers:
            obs.update()

    def _get_all_affected(self, cells):
        for observer in self.observers:
            if observer not in cells:
                cells.append(observer)
                observer._get_all_affected(cells)

    def fire_callbacks(self):
        for cb in self.callbacks:
            cb(self._value)

    def add_callback(self, callback):
        self.callbacks.add(callback)

    def remove_callback(self, callback):
        self.callbacks.discard(callback)