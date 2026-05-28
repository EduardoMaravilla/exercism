public class LogLevels {
    
    public static String message(String logLine) {
        String[] parts = logLine.split(":");
       return parts[1].trim();
    }

    public static String logLevel(String logLine) {
        String[] parts = logLine.split(":");
        return parts[0].replaceAll("[^a-zA-Z]","").toLowerCase();
    }

    public static String reformat(String logLine) {
        return  message(logLine) + " (" +logLevel(logLine)+")";
    }
}
