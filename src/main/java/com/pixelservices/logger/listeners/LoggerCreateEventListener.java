package com.pixelservices.logger.listeners;

import com.pixelservices.logger.events.LoggerCreateEvent;

public interface LoggerCreateEventListener extends Listener {
    void onCreateEvent(LoggerCreateEvent event);
}
