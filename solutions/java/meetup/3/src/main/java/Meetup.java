import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Meetup {
    private final int month;
    private final int year;

    public Meetup(int month, int year) {
        this.month = month;
        this.year = year;
    }

    public LocalDate day(DayOfWeek dayOfWeek, MeetupSchedule meetupSchedule) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate firstOfMonth = yearMonth.atDay(1);
        LocalDate lastOfMonth = yearMonth.atEndOfMonth();

        return switch (meetupSchedule) {
            case FIRST -> findDay(firstOfMonth, dayOfWeek, 1, 7);
            case SECOND -> findDay(firstOfMonth, dayOfWeek, 8, 14);
            case THIRD -> findDay(firstOfMonth, dayOfWeek, 15, 21);
            case FOURTH -> findDay(firstOfMonth, dayOfWeek, 22, 28);
            case LAST -> findDayReverse(lastOfMonth, dayOfWeek, 1, 7);
            case TEENTH -> findDay(firstOfMonth, dayOfWeek, 13, 19);
            default -> throw new IllegalArgumentException("Invalid Meetup Schedule");
        };
    }

    private LocalDate findDay(LocalDate startDate, DayOfWeek dayOfWeek, int startDay, int endDay) {
        return Stream.iterate(startDate.withDayOfMonth(startDay), date -> date.plusDays(1))
                .limit(endDay - startDay + 1)
                .filter(date -> date.getDayOfWeek() == dayOfWeek)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No valid date found"));
    }

    private LocalDate findDayReverse(LocalDate endDate, DayOfWeek dayOfWeek, int startDay, int endDay) {
        return Stream.iterate(endDate.withDayOfMonth(endDate.getDayOfMonth() - startDay + 1), date -> date.minusDays(1))
                .limit(endDay)
                .filter(date -> date.getDayOfWeek() == dayOfWeek)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No valid date found"));
    }
}
