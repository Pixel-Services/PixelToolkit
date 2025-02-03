package com.pixelservices.logger;

import com.pixelservices.logger.events.LogEvent;
import com.pixelservices.logger.formatter.LogFormatter;
import com.pixelservices.logger.level.Level;
import com.pixelservices.logger.listeners.LoggerLogEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * The Logger class provides a simple logging mechanism with different log levels and event listeners.
 */
public class Logger {
    private final String name;
    private final List<LoggerLogEventListener> listeners = new ArrayList<>();
    private LogFormatter formatter;

    /**
     * Constructs a Logger with the specified name and formatter.
     *
     * @param name the name of the logger
     * @param formatter the formatter to format log messages
     */
    protected Logger(String name, LogFormatter formatter) {
        this.name = name;
        this.formatter = formatter;
    }

    /**
     * Returns a Logger for the specified class.
     *
     * @param clazz the class for which to get the logger
     * @return the logger for the specified class
     */
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz.getSimpleName());
    }

    /**
     * Logs an informational message.
     *
     * @param message the message to log
     */
    public void info(String message) {
        log(Level.INFO, message);
    }

    /**
     * Logs a warning message.
     *
     * @param message the message to log
     */
    public void warn(String message) {
        log(Level.WARN, message);
    }

    /**
     * Logs an error message.
     *
     * @param message the message to log
     */
    public void error(String message) {
        log(Level.ERROR, message);
    }

    /**
     * Logs an error message with the specified throwable.
     *
     * @param throwable the throwable to log
     */
    public void error(Throwable throwable) {
        log(Level.ERROR, formatStackTrace(throwable));
    }

    /**
     * Logs an error message with the specified message and throwable.
     *
     * @param message the message to log
     * @param throwable the throwable to log
     */
    public void error(String message, Throwable throwable) {
        log(Level.ERROR, message + "\n" + formatStackTrace(throwable));
    }

    /**
     * Logs a debug message.
     *
     * @param message the message to log
     */
    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    /**
     * Adds a log event listener to the logger.
     *
     * @param listener the log event listener to add
     */
    protected void addLogEventListener(LoggerLogEventListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes a log event listener from the logger.
     *
     * @param listener the log event listener to remove
     */
    protected void removeLogEventListener(LoggerLogEventListener listener) {
        listeners.remove(listener);
    }

    /**
     * Sets the log formatter.
     *
     * @param formatter the formatter to set
     */
    protected void setFormatter(LogFormatter formatter) {
        this.formatter = formatter;
    }

    /**
     * Logs a message with the specified level.
     *
     * @param level the level of the log message
     * @param message the message to log
     */
    private void log(Level level, String message) {
        LogEvent event = new LogEvent(level, name, message);
        boolean isCancelled = notifyListeners(level, message);
        if (isCancelled) return;
        String formattedMessage = formatter.format(event);
        System.out.println(formattedMessage);
    }

    /**
     * Notifies all registered listeners of a log event.
     *
     * @param level the level of the log message
     * @param message the message to log
     * @return true if the event is cancelled, false otherwise
     */
    private boolean notifyListeners(Level level, String message) {
        LogEvent event = new LogEvent(level, name, message);
        for (LoggerLogEventListener listener : listeners) {
            listener.onLogEvent(event);
        }
        return event.isCancelled();
    }

    /**
     * Formats the stack trace of a throwable to a more readable format.
     *
     * @param throwable the throwable to format
     * @return the formatted stack trace
     */
    private String formatStackTrace(Throwable throwable) {
        StringBuilder sb = new StringBuilder();
        sb.append(throwable.toString()).append("\n");
        for (StackTraceElement element : throwable.getStackTrace()) {
            sb.append("\tat ").append(element).append("\n");
        }
        Throwable cause = throwable.getCause();
        if (cause != null) {
            sb.append("Caused by: ").append(formatStackTrace(cause));
        }
        return sb.toString();
    }
}
