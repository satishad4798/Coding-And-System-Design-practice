package lld.designPattern.behavioural.chainOfResponsibiltyPattern;

public class Main {

    public static void main(String[] args) {
        Logger logger = new DebugLogger(new InfoLogger(new ErrorLogger(null)));

        // Set minimum log level if needed
        logger.setMinLogLevel(LogLevel.INFO);

        // Test all log levels
        logger.log(LogLevel.INFO, "This is an informational message");
        logger.log(LogLevel.DEBUG, "This is a debug message");
        logger.log(LogLevel.ERROR, "This is an error message");

        // Test with different minimum log level
        logger.setMinLogLevel(LogLevel.ERROR);
        logger.log(LogLevel.INFO, "This info message won't be printed");
        logger.log(LogLevel.ERROR, "This error message will be printed");
    }
}
