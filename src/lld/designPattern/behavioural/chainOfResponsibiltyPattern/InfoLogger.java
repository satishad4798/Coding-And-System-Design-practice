package lld.designPattern.behavioural.chainOfResponsibiltyPattern;

public class InfoLogger extends LogManager {
    public InfoLogger(Logger nextLogger) {
        super(nextLogger);
    }

    @Override
    public void log(LogLevel level, String message) {
        if (level.getLevel() < minLogLevel.getLevel()) {
            return;
        }

        if (level == LogLevel.INFO) {
            System.out.println(formatMessage(level, message));
        } else if (nextLogger != null) {
            super.nextLogger(level, message);
        }
    }
}
