import calendar
from datetime import date, timedelta


# subclassing the built-in ValueError to create MeetupDayException
class MeetupDayException(ValueError):
    """Exception raised when the Meetup weekday and count do not result in a valid date.

    message: explanation of the error.

    """
    def __init__(self, s):
        super().__init__(s)


def meetup(year, month, schedule, day_of_week):
    days_map = {
        "Monday": 0,
        "Tuesday": 1,
        "Wednesday": 2,
        "Thursday": 3,
        "Friday": 4,
        "Saturday": 5,
        "Sunday": 6,
    }

    target = days_map[day_of_week]

    _, last_day = calendar.monthrange(year, month)
    matching_days = [
        date(year, month, d)
        for d in range(1, last_day + 1)
        if date(year, month, d).weekday() == target
    ]

    if schedule == "teenth":
        for d in matching_days:
            if 13 <= d.day <= 19:
                return d

    elif schedule == "last":
        return matching_days[-1]

    else:
        index_map = {
            "first": 0,
            "second": 1,
            "third": 2,
            "fourth": 3,
            "fifth": 4,
        }

        idx = index_map.get(schedule)

        if idx is not None and idx < len(matching_days):
            return matching_days[idx]

    raise MeetupDayException("That day does not exist.")