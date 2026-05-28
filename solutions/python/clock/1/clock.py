class Clock:
    def __init__(self, hour, minute):
        total_minutes = (hour * 60 + minute) % 1440
        if total_minutes < 0:
            total_minutes += 1440
        self.hour = total_minutes // 60
        self.minute = total_minutes % 60

    def __repr__(self):
        return f"Clock({self.hour}, {self.minute})"

    def __str__(self):
        return f"{self.hour:02}:{self.minute:02}"

    def __eq__(self, other):
        if not isinstance(other, Clock):
            return False
        return self.hour == other.hour and self.minute == other.minute

    def __add__(self, minutes):
        return Clock(self.hour, self.minute + minutes)

    def __sub__(self, minutes):
        return Clock(self.hour, self.minute - minutes)
