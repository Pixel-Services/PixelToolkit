package com.pixelservices.logger;

import com.pixelservices.logger.events.LogEvent;
import com.pixelservices.logger.formatter.LogFormatter;
import com.pixelservices.logger.level.Level;
import com.pixelservices.logger.listeners.LoggerLogEventListener;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private final String name;
    private final List<LoggerLogEventListener> listeners = new ArrayList<>();
    private LogFormatter formatter;

    protected Logger(String name, LogFormatter formatter) {
        this.name = name;
        this.formatter = formatter;
    }

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz.getName());
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void warn(String message) {
        log(Level.WARN, message);
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }

    public void error(Throwable throwable) {
        log(Level.ERROR, throwable.getMessage());
    }

    public void error(String message, Throwable throwable) {
        log(Level.ERROR, message + "\n" + throwable.getMessage());
    }

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    protected void addLogEventListener(LoggerLogEventListener listener) {
        listeners.add(listener);
    }

    protected void removeLogEventListener(LoggerLogEventListener listener) {
        listeners.remove(listener);
    }

    protected void setFormatter(LogFormatter formatter) {
        this.formatter = formatter;
    }

    private void log(Level level, String message) {
        LogEvent event = new LogEvent(level, name, message);
        boolean isCancelled = notifyListeners(level, message);
        if (isCancelled) return;
        String formattedMessage = formatter.format(event);
        System.out.println(formattedMessage);
    }

    private boolean notifyListeners(Level level, String message) {
        LogEvent event = new LogEvent(level, name, message);
        for (LoggerLogEventListener listener : listeners) {
            listener.onLogEvent(event);
        }
        return event.isCancelled();
    }
}
