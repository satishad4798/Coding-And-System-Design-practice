package lld.designPattern.behavioural.chainOfResponsibiltyPattern;

public interface Logger {
    void log(LogLevel level, String message);

    /**
     * Sets the minimum log level. Messages below this level will not be logged.
     */
    default void setMinLogLevel(LogLevel level) {
    }
}
