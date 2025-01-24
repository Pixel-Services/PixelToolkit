package com.pixelservices.logger.formatter;

import com.pixelservices.logger.events.LogEvent;

public interface LogFormatter {
    String format(LogEvent event);
}