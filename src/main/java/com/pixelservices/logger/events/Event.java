package com.pixelservices.logger.events;

/**
 * Represents a generic event with a timestamp.
 */
public class Event {
    private final long timestamp;

    /**
     * Constructs a new Event with the current system time as the timestamp.
     */
    public Event() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * Returns the timestamp of the event.
     *
     * @return the timestamp of the event in milliseconds since the epoch.
     */
    public long getTimestamp() {
        return timestamp;
    }
}
