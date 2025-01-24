package com.pixelservices.logger.formatter;

import com.pixelservices.logger.events.LogEvent;

/**
 * A simple implementation of the LogFormatter interface that formats log events into a string.
 */
public class SimpleLogFormatter implements LogFormatter {

    /**
     * Formats the given log event into a string.
     *
     * @param event the log event to format
     * @return the formatted log event as a string
     */
    @Override
    public String format(LogEvent event) {
        return String.format("%s %s: %s", String.format("%-" + 7 + "s", "[" + event.getLevel() + "]"), String.format("%-" + 12 + "s", event.getLoggerName()), event.getMessage());
    }
}