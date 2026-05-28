import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class RateLimiter<K> {

    private final int limit;
    private final Duration windowSize;
    private final TimeSource timeSource;
    private final Map<K, Window> clients = new HashMap<>();

    private static class Window {
        Instant windowStart;
        int count;

        Window(Instant windowStart) {
            this.windowStart = windowStart;
            this.count = 0;
        }
    }

    public RateLimiter(int limit, Duration windowSize, TimeSource timeSource) {
        this.limit = limit;
        this.windowSize = windowSize;
        this.timeSource = timeSource;
    }

    public boolean allow(K clientId) {
        Instant now = timeSource.now();
        Window window = clients.computeIfAbsent(clientId, k -> new Window(now));

        if (Duration.between(window.windowStart, now).compareTo(windowSize) >= 0) {
            window.windowStart = now;
            window.count = 0;
        }

        if (window.count < limit) {
            window.count++;
            return true;
        } else {
            return false;
        }
    }
}