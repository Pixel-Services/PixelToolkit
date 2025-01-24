package com.pixelservices.logger.events;

import com.pixelservices.logger.level.Level;

public class LogEvent extends CancellableEvent {
    private final Level level;
    private final String loggerName;
    private final String message;

    public LogEvent(Level level, String loggerName, String message) {
        this.level = level;
        this.loggerName = loggerName;
        this.message = message;
    }

    public Level getLevel() {
        return level;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public String getMessage() {
        return message;
    }
}