package lld.Log;

public class DebugLogger extends LogManager {
    public DebugLogger(Logger logger) {
        super(logger);
    }

    @Override
    public void log(LogLevel level, String message) {
        if (level.getLevel() < minLogLevel.getLevel()) {
            return;
        }

        if (level == LogLevel.DEBUG) {
            System.out.println(formatMessage(level, message));
        } else if (nextLogger != null) {
            super.nextLogger(level, message);
        }
    }
}
