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

public class LoggerFactory {
    private static final ConcurrentMap<String, Logger> loggerCache = new ConcurrentHashMap<>();
    private static final List<Listener> listeners = new ArrayList<>();
    private static LogFormatter formatter = new SimpleLogFormatter();

    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getSimpleName());
    }

    public static Logger getLogger(String name) {
        return loggerCache.computeIfAbsent(name, n -> createLogger(name));
    }

    public static void setFormatter(LogFormatter formatter) {
        LoggerFactory.formatter = formatter;
        for (Logger logger : loggerCache.values()) {
            logger.setFormatter(formatter);
        }
    }

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