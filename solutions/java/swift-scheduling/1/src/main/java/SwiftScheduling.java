import java.time.*;

public class SwiftScheduling {
    public static LocalDateTime convertToDeliveryDate(LocalDateTime meetingStart, String description) {
        LocalDateTime schedulingMeeting;

        if (description.equals("NOW")) {
            return meetingStart.plusHours(2L);
        } else if (description.equals("ASAP")) {
            if (isASAPBefore13(meetingStart)) {
                schedulingMeeting = meetingStart.withHour(17).withMinute(0).withSecond(0).withNano(0);
            } else {
                schedulingMeeting = meetingStart.plusDays(1).withHour(13).withMinute(0).withSecond(0).withNano(0);
            }
        } else if (description.equals("EOW")) {
            int[] eowData = getEOWData(meetingStart);
            schedulingMeeting = LocalDateTime.of(eowData[0], eowData[1], eowData[2], eowData[3], 0);
        } else if (description.matches("^\\d{1,2}M$")) {
            int targetMonth = Integer.parseInt(description.replace("M", ""));
            schedulingMeeting = resolveMonthlyDelivery(meetingStart, targetMonth);
        } else if (description.matches("^Q[1-4]$")) {
            int targetQuarter = Integer.parseInt(description.substring(1));
            schedulingMeeting = resolveQuarterlyDelivery(meetingStart, targetQuarter);
        } else {
            schedulingMeeting = LocalDateTime.now();
        }

        return schedulingMeeting;
    }

    private static LocalDateTime resolveMonthlyDelivery(LocalDateTime meetingStart, int targetMonth) {
        int deliveryYear = (meetingStart.getMonthValue() < targetMonth) ? meetingStart.getYear() : meetingStart.getYear() + 1;
        LocalDate firstOfMonth = LocalDate.of(deliveryYear, targetMonth, 1);
        LocalDate firstWorkday = getFirstWorkdayOfMonth(firstOfMonth);
        return firstWorkday.atTime(8, 0);
    }

    private static LocalDateTime resolveQuarterlyDelivery(LocalDateTime meetingStart, int targetQuarter) {
        int currentQuarter = (meetingStart.getMonthValue() - 1) / 3 + 1;
        int deliveryYear = (currentQuarter > targetQuarter) ? meetingStart.getYear() + 1 : meetingStart.getYear();

        Month endMonth = Month.of(targetQuarter * 3);
        YearMonth ym = YearMonth.of(deliveryYear, endMonth);
        LocalDate lastOfQuarter = ym.atEndOfMonth();
        LocalDate lastWorkday = getLastWorkdayOfMonth(lastOfQuarter);
        return lastWorkday.atTime(8, 0);
    }

    private static LocalDate getFirstWorkdayOfMonth(LocalDate firstDay) {
        DayOfWeek dow = firstDay.getDayOfWeek();
        if (dow == DayOfWeek.SATURDAY) {
            return firstDay.plusDays(2);
        } else if (dow == DayOfWeek.SUNDAY) {
            return firstDay.plusDays(1);
        }
        return firstDay;
    }

    private static LocalDate getLastWorkdayOfMonth(LocalDate lastDay) {
        DayOfWeek dow = lastDay.getDayOfWeek();
        if (dow == DayOfWeek.SATURDAY) {
            return lastDay.minusDays(1);
        } else if (dow == DayOfWeek.SUNDAY) {
            return lastDay.minusDays(2);
        }
        return lastDay;
    }

    private static int[] getEOWData(LocalDateTime meetingStart) {
        int dayOfWeek = meetingStart.getDayOfWeek().getValue();
        LocalDateTime adjustedDate;

        if (dayOfWeek >= 1 && dayOfWeek <= 3) {
            adjustedDate = meetingStart.plusDays(5L - dayOfWeek).withHour(17).withMinute(0);
        } else {
            adjustedDate = meetingStart.plusDays(7L - dayOfWeek).withHour(20).withMinute(0);
        }

        return new int[]{
                adjustedDate.getYear(),
                adjustedDate.getMonthValue(),
                adjustedDate.getDayOfMonth(),
                adjustedDate.getHour()
        };
    }

    public static boolean isASAPBefore13(LocalDateTime meeting) {
        return meeting.getHour() < 13;
    }
}