package lld.Log;

public abstract class LogManager implements Logger {
    protected final Logger nextLogger;
    protected LogLevel minLogLevel;

    LogManager(Logger nextLogger) {
        this.nextLogger = nextLogger;
        this.minLogLevel = LogLevel.INFO; // Default to INFO
    }

    @Override
    public void setMinLogLevel(LogLevel level) {
        this.minLogLevel = level;
        if (nextLogger != null) {
            nextLogger.setMinLogLevel(level);
        }
    }

    protected void nextLogger(LogLevel level, String message) {
        if (nextLogger != null) {
            nextLogger.log(level, message);
        }
    }

    protected String formatMessage(LogLevel level, String message) {
        return String.format("[%s][%s][Thread: %s] %s",
                java.time.LocalDateTime.now(),
                level,
                Thread.currentThread().getName(),
                message
        );
    }
}
