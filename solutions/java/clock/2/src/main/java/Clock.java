import java.util.Objects;

public class Clock {

    private int hour;
    private int minute;

    public Clock(int hour, int minute) {
        adjustTime(hour, minute);
    }

    private void adjustTime(int hour, int minute) {
        int totalMinutes = (hour * 60 + minute) % (24 * 60);
        if (totalMinutes < 0) {
            totalMinutes = 24 * 60 + totalMinutes;
        }
        this.hour = totalMinutes / 60;
        this.minute = totalMinutes % 60;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minute);
    }
    
    public void add(int number){
        adjustTime(hour, minute + number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour, minute);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Clock other = (Clock) obj;
        return hour == other.hour && minute == other.minute;
    }
}