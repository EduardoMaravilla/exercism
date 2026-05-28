import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SplitSecondStopwatch {

    private enum State { READY, RUNNING, STOPPED }

    private State state;
    private long finishTime;
    private final List<String> previousLaps;

    public SplitSecondStopwatch() {
        this.state = State.READY;
        this.previousLaps = new ArrayList<>();
    }

    public void start() {
        if (state != State.RUNNING) {
            state = State.RUNNING;
        } else {
            throw new IllegalStateException("cannot start an already running stopwatch");
        }
    }

    public void stop() {
        if (state == State.RUNNING) {
            state = State.STOPPED;
        } else {
            throw new IllegalStateException("cannot stop a stopwatch that is not running");
        }
    }

    public void reset() {
        if (state != State.STOPPED) {
            throw new IllegalStateException("cannot reset a stopwatch that is not stopped");
        }
        state = State.READY;
        finishTime = 0;
        previousLaps.clear();
    }

    public void lap() {
        if (state != State.RUNNING) {
            throw new IllegalStateException("cannot lap a stopwatch that is not running");
        }
        previousLaps.add(currentLap());
        finishTime = 0;
    }

    public String state() {
        return state.name().toLowerCase();
    }

    public String currentLap() {
        return formatTime(finishTime);
    }

    public String total() {
        long totalTime = previousLaps.stream()
                .mapToLong(SplitSecondStopwatch::parseTime)
                .sum() + finishTime;

        return formatTime(totalTime);
    }

    public List<String> previousLaps() {
        return Collections.unmodifiableList(previousLaps);
    }

    public void advanceTime(String timeString) {
        if (state != State.STOPPED) {
            finishTime += parseTime(timeString);
        }
    }

    private static String formatTime(long millis) {
        long totalSeconds = millis / 1000;
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private static long parseTime(String timeString) {
        String[] parts = timeString.split(":");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid format. Use hh:mm:ss");
        }
        try {
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            int seconds = Integer.parseInt(parts[2]);
            return (hours * 3600L + minutes * 60L + seconds) * 1000L;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in time string");
        }
    }
}