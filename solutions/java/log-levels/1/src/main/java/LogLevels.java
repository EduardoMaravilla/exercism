public class LogLevels {
    
    public static String message(String logLine) {
       String[] parts = logLine.split(":");
       return parts[1].trim();
               
    }

    public static String logLevel(String logLine) {
        logLine=logLine.replace("[","").replace("]","");
       String[] logLine2 = logLine.split(":");
    String subLogLine2 = logLine2[0].trim();
    String lowercaseLevel = subLogLine2.toLowerCase();
    return lowercaseLevel;      
        
    }

    public static String reformat(String logLine) {
        logLine=logLine.replace("[","").replace("]","");
        String[] logLineParts = logLine.split(":\\s+");
    String logLevel = logLineParts[0].toLowerCase();
    String message = logLineParts[1].trim();
    return message + " (" + logLevel + ")";        
    }
}
