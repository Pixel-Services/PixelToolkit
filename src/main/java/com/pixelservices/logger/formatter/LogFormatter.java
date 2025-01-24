package com.pixelservices.logger.formatter;

import com.pixelservices.logger.events.LogEvent;

/**
 * Defines the contract for formatting log events.
 */
public interface LogFormatter {

    /**
     * Formats the given log event into a string.
     *
     * @param event the log event to format
     * @return the formatted log event as a string
     */
    String format(LogEvent event);
}