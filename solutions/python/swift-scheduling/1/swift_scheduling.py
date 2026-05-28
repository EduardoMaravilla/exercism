import re
from datetime import datetime, timedelta
import calendar


def _get_eow_data(start):
    day_of_week = start.weekday()

    if 0 <= day_of_week <= 2:
        days_to_add = 4 - day_of_week
        hour = 17
    else:
        days_to_add = 6 - day_of_week
        hour = 20

    result = start + timedelta(days=days_to_add)
    return result.replace(hour=hour, minute=0, second=0, microsecond=0)


def _first_work_day_of_month(first_day):
    if first_day.weekday() == 5:
        return first_day + timedelta(days=2)
    elif first_day.weekday() == 6:
        return first_day + timedelta(days=1)
    return first_day


def _last_work_day_of_month(last_day):
    if last_day.weekday() == 5:
        return last_day - timedelta(days=1)
    elif last_day.weekday() == 6:
        return last_day - timedelta(days=2)
    return last_day


def _resolved_monthly_delivery(start, target_month):
    delivery_year = start.year if start.month < target_month else start.year + 1
    first_of_month = datetime(delivery_year, target_month, 1)
    first_work_day = _first_work_day_of_month(first_of_month)
    return first_work_day.replace(hour=8, minute=0, second=0, microsecond=0)


def _resolved_quarterly_delivery(start, target_quarter):
    current_quarter = (start.month - 1) // 3 + 1
    delivery_year = start.year + 1 if current_quarter > target_quarter else start.year

    end_month = target_quarter * 3

    last_day = calendar.monthrange(delivery_year, end_month)[1]
    last_of_quarter = datetime(delivery_year, end_month, last_day)

    last_work_day = _last_work_day_of_month(last_of_quarter)
    return last_work_day.replace(hour=8, minute=0, second=0, microsecond=0)


def delivery_date(start, description):
    normalize_date = datetime.fromisoformat(start)

    if description == "NOW":
        return (normalize_date + timedelta(hours=2)).isoformat()

    elif description == "ASAP":
        if normalize_date.hour < 13:
            scheduling_meeting = normalize_date.replace(
                hour=17, minute=0, second=0, microsecond=0
            )
        else:
            scheduling_meeting = (normalize_date + timedelta(days=1)).replace(
                hour=13, minute=0, second=0, microsecond=0
            )

    elif description == "EOW":
        scheduling_meeting = _get_eow_data(normalize_date)

    elif re.match(r"^\d{1,2}M$", description):
        target_month = int(description.replace("M", ""))
        scheduling_meeting = _resolved_monthly_delivery(normalize_date, target_month)

    elif re.match(r"^Q[1-4]$", description):
        target_quarter = int(description[1])
        scheduling_meeting = _resolved_quarterly_delivery(normalize_date, target_quarter)

    else:
        scheduling_meeting = datetime.now()

    return scheduling_meeting.isoformat()
