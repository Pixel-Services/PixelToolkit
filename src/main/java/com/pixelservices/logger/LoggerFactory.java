package com.pixelservices.logger;

import com.pixelservices.logger.events.LoggerCreateEvent;
import com.pixelservices.logger.formatter.LogFormatter;
import com.pixelservices.logger.formatter.SimpleLogFormatter;
import com.pixelservices.logger.listeners.Listener;
import com.pixelservices.logger.listeners.LoggerCreateEventListener;
import com.pixelservices.logger.listeners.LoggerLogEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Factory class for creating and managing Logger instances.
 */
public class LoggerFactory {
    private static final ConcurrentMap<String, Logger> loggerCache = new ConcurrentHashMap<>();
    private static final List<Listener> listeners = new ArrayList<>();
    private static LogFormatter formatter = new SimpleLogFormatter();

    /**
     * Returns a Logger for the specified class.
     *
     * @param clazz the class for which to get the logger
     * @return the logger for the specified class
     */
    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getSimpleName());
    }

    /**
     * Returns a Logger with the specified name.
     *
     * @param name the name of the logger
     * @return the logger with the specified name
     */
    public static Logger getLogger(String name) {
        return loggerCache.computeIfAbsent(name, n -> createLogger(name));
    }

    /**
     * Sets the formatter for all loggers.
     *
     * @param formatter the formatter to set
     */
    public static void setFormatter(LogFormatter formatter) {
        LoggerFactory.formatter = formatter;
        for (Logger logger : loggerCache.values()) {
            logger.setFormatter(formatter);
        }
    }

    /**
     * Registers a listener to handle logger events.
     *
     * @param listener the listener to register
     * @throws IllegalArgumentException if the listener is null or already registered
     */
    public static void registerListener(Listener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener cannot be null");
        }

        if (listener instanceof LoggerCreateEventListener) {
            if (listeners.contains(listener)) {
                throw new IllegalArgumentException("Listener is already registered");
            }
            listeners.add(listener);
        } else if (listener instanceof LoggerLogEventListener) {
            for (Logger logger : loggerCache.values()) {
                logger.addLogEventListener((LoggerLogEventListener) listener);
            }
            listeners.add(listener);
        } else {
            throw new IllegalArgumentException("Listener must be an instance of a valid listener type");
        }
    }

    /**
     * Unregisters a log event listener.
     *
     * @param listener the listener to unregister
     * @throws IllegalArgumentException if the listener is null or not registered
     */
    public static void unregisterListener(LoggerLogEventListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener cannot be null");
        }

        if (listeners.contains(listener)) {
            listeners.remove(listener);
            for (Logger logger : loggerCache.values()) {
                logger.removeLogEventListener(listener);
            }
        } else {
            throw new IllegalArgumentException("Listener is not registered");
        }
    }

    /**
     * Creates a new Logger with the specified name.
     *
     * @param name the name of the logger
     * @return the created logger
     */
    private static Logger createLogger(String name) {
        Logger logger = new Logger(name, formatter);
        LoggerCreateEvent event = new LoggerCreateEvent(logger);
        for (Listener listener : listeners) {
            if (listener instanceof LoggerCreateEventListener) {
                ((LoggerCreateEventListener) listener).onCreateEvent(event);
            } else if (listener instanceof LoggerLogEventListener) {
                logger.addLogEventListener((LoggerLogEventListener) listener);
            }
        }
        logger.setFormatter(formatter);
        return logger;
    }
}