package com.pixelservices.logger.listeners;

import com.pixelservices.logger.events.LoggerCreateEvent;

/**
 * Represents a listener that handles logger creation events.
 */
public interface LoggerCreateEventListener extends Listener {
    /**
     * Called when a logger creation event occurs.
     *
     * @param event the logger creation event
     */
    void onCreateEvent(LoggerCreateEvent event);
}