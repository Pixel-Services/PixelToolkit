package com.pixelservices.logger.events;

/**
 * Represents an event that can be cancelled.
 */
public class CancellableEvent extends Event {
    private boolean cancelled = false;

    /**
     * Checks if the event is cancelled.
     *
     * @return true if the event is cancelled, false otherwise.
     */
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Sets the cancelled status of the event.
     *
     * @param cancelled true to cancel the event, false to uncancel it.
     */
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
