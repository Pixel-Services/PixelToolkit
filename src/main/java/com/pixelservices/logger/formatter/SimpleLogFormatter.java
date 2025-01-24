package com.pixelservices.logger.formatter;

import com.pixelservices.logger.events.LogEvent;

public class SimpleLogFormatter implements LogFormatter {
    @Override
    public String format(LogEvent event) {
        return String.format("%s %s: %s", String.format("%-" + 7 + "s", "[" + event.getLevel() + "]"), String.format("%-" + 12 + "s", event.getLoggerName()), event.getMessage());
    }
}