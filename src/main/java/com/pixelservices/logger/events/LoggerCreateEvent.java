package com.pixelservices.logger.events;

import com.pixelservices.logger.Logger;

public class LoggerCreateEvent extends CancellableEvent {
    private final Logger logger;

    public LoggerCreateEvent(Logger logger) {
        this.logger = logger;
    }

    public Logger getLoggerName() {
        return logger;
    }
}
