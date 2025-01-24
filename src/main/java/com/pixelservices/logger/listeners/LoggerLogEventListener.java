package com.pixelservices.logger.listeners;

import com.pixelservices.logger.events.LogEvent;

public interface LoggerLogEventListener extends Listener {
    void onLogEvent(LogEvent event);
}