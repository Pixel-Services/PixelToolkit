package com.pixelservices.logger.events;

import com.pixelservices.logger.Logger;

/**
 * Represents an event that is triggered when a logger is created.
 */
public class LoggerCreateEvent extends CancellableEvent {
    private final Logger logger;

    /**
     * Constructs a new LoggerCreateEvent with the specified logger.
     *
     * @param logger the logger that was created
     */
    public LoggerCreateEvent(Logger logger) {
        this.logger = logger;
    }

    /**
     * Returns the logger that was created.
     *
     * @return the logger that was created
     */
    public Logger getLoggerName() {
        return logger;
    }
}
