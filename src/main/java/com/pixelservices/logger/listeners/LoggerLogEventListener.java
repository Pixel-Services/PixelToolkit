package com.pixelservices.logger.listeners;

import com.pixelservices.logger.events.LogEvent;

/**
 * Represents a listener that handles log events.
 */
public interface LoggerLogEventListener extends Listener {
    /**
     * Called when a log event occurs.
     *
     * @param event the log event
     */
    void onLogEvent(LogEvent event);
}