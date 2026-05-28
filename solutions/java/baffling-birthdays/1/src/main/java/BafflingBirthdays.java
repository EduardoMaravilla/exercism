import java.time.LocalDate;
import java.time.MonthDay;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class BafflingBirthdays {

    private static final int DAYS_IN_YEAR = 365;
    private static final Random RANDOM = new Random();

    boolean sharedBirthday(List<LocalDate> birthdates) {
        Set<MonthDay> seen = new HashSet<>();

        for (LocalDate date : birthdates) {
            MonthDay monthDay = MonthDay.from(date);
            if (!seen.add(monthDay)) {
                return true;
            }
        }
        return false;
    }

    List<LocalDate> randomBirthdates(int groupSize) {
        return IntStream.range(0, groupSize)
                .mapToObj(i -> randomNonLeapDate())
                .collect(Collectors.toList());
    }

    private LocalDate randomNonLeapDate() {
        int year;
        do {
            year = 1900 + RANDOM.nextInt(200); 
        } while (LocalDate.of(year, 1, 1).isLeapYear());

        int dayOfYear = 1 + RANDOM.nextInt(DAYS_IN_YEAR);
        return LocalDate.ofYearDay(year, dayOfYear);
    }

    double estimatedProbabilityOfSharedBirthday(int groupSize) {
        if (groupSize <= 1) {
            return 0.0;
        }

        double probabilityAllDifferent = 1.0;

        for (int i = 0; i < groupSize; i++) {
            probabilityAllDifferent *= (DAYS_IN_YEAR - i) / (double) DAYS_IN_YEAR;
        }

        double probabilityAtLeastOneShared = 1 - probabilityAllDifferent;
        return probabilityAtLeastOneShared * 100;
    }
}