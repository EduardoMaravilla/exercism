import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.YearMonth;

public class Meetup {
    private final int month;
    private final int year;

    Meetup(int month, int year) {
        this.month = month;
        this.year = year;
    }

    public LocalDate day(DayOfWeek dayOfWeek, MeetupSchedule meetupSchedule) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate firstOfMonth = yearMonth.atDay(1);
        LocalDate lastOfMonth = yearMonth.atEndOfMonth();

        return switch (meetupSchedule) {
            case FIRST -> findDayInWeek(firstOfMonth, dayOfWeek);
            case SECOND -> findDayInWeek(firstOfMonth.plusDays(7), dayOfWeek);
            case THIRD -> findDayInWeek(firstOfMonth.plusDays(14), dayOfWeek);
            case FOURTH -> findDayInWeek(firstOfMonth.plusDays(21), dayOfWeek);
            case LAST ->  findLastDayInWeek(lastOfMonth.minusDays(6), dayOfWeek);
            case TEENTH -> findTeenthDay(firstOfMonth, dayOfWeek);
            default -> LocalDate.MIN;
        };
    }

    private LocalDate findDayInWeek(LocalDate startDate, DayOfWeek dayOfWeek) {
        LocalDate date = startDate;
        while (date.getDayOfWeek() != dayOfWeek) {
            date = date.plusDays(1);
        }
        return date;
    }

    private LocalDate findTeenthDay(LocalDate startDate, DayOfWeek dayOfWeek) {
        LocalDate date = startDate;
        while (date.getDayOfMonth() < 13 || date.getDayOfMonth() > 19 || date.getDayOfWeek() != dayOfWeek) {
            date = date.plusDays(1);
        }
        return date;
    }

    private LocalDate findLastDayInWeek(LocalDate startDate, DayOfWeek dayOfWeek) {
    LocalDate date = startDate;
    while (date.getDayOfWeek() != dayOfWeek) {
        date = date.plusDays(1);
    }
    return date;
}
}