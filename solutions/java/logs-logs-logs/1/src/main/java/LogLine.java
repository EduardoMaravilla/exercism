import java.util.Arrays;

public class LogLine {
    private String message;
    private String logLevel;

    public LogLine(String logLine) {
        String[] texts = logLine.split(":");
        this.message=texts[1].trim();
        this.logLevel=texts[0].replaceAll("[^a-zA-Z]","");
    }

    public LogLevel getLogLevel() {
        return Arrays.stream(LogLevel.values()).filter(log -> log.getMsg().equals(this.logLevel)).findAny().orElse(LogLevel.UNKNOWN);
    }

    public String getOutputForShortLog() {
        return getLogLevel().getVal()+":"+message;
    }
}

