package com.pixelservices.logger.events;

import com.pixelservices.logger.level.Level;

/**
 * Represents a log event that can be cancelled or modified.
 */
public class LogEvent extends CancellableEvent {
    private Level level;
    private String loggerName;
    private String message;

    /**
     * Constructs a new LogEvent with the specified level, logger name, and message.
     *
     * @param level the level of the log event
     * @param loggerName the name of the logger
     * @param message the log message
     */
    public LogEvent(Level level, String loggerName, String message) {
        this.level = level;
        this.loggerName = loggerName;
        this.message = message;
    }

    /**
     * Returns the level of the log event.
     *
     * @return the level of the log event
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Sets the level of the log event.
     *
     * @param level the new level of the log event
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * Returns the name of the logger.
     *
     * @return the name of the logger
     */
    public String getLoggerName() {
        return loggerName;
    }

    /**
     * Sets the name of the logger.
     *
     * @param loggerName the new name of the logger
     */
    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    /**
     * Returns the log message.
     *
     * @return the log message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the log message.
     *
     * @param message the new log message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}