package lld.Log;

public class ErrorLogger extends LogManager {
    public ErrorLogger(Logger logger) {
        super(logger);
    }

    @Override
    public void log(LogLevel level, String message) {
        if (level.getLevel() < minLogLevel.getLevel()) {
            return;
        }

        if (level == LogLevel.ERROR) {
            System.err.println(formatMessage(level, message));
        } else if (nextLogger != null) {
            super.nextLogger(level, message);
        }
    }
}
