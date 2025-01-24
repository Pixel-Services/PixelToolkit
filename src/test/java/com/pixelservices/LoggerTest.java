package com.pixelservices;

import com.pixelservices.logger.Logger;
import com.pixelservices.logger.LoggerFactory;
import com.pixelservices.logger.level.Level;
import com.pixelservices.logger.listeners.LoggerLogEventListener;
import org.junit.Test;

public class LoggerTest {
    private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void testLogger() {
        // Test if the logger works
        logger.info("Hello, World!");
        logger.warn("This is a warning");
        logger.error("This is an error");
        logger.debug("This is a debug message");
    }

    @Test
    public void testListeners() {
        // Test if the listener works
        LoggerLogEventListener listener = event -> {
            if (event.getLevel() == Level.INFO){
                event.setCancelled(true);
                logger.debug("Event cancelled: " + event.getMessage());
            }
        };

        LoggerFactory.registerListener(listener);
        logger.info("This message should be cancelled");

        // Test if new logger instances are affected by the listener
        Logger logger2 = LoggerFactory.getLogger("LoggerTest2");
        logger2.info("This message should be cancelled too");

        // Test if unregistering the listener works
        LoggerFactory.unregisterListener(listener);
        logger.info("This message should be logged");
        logger2.info("This message should be logged too");
    }

    @Test
    public void testSetFormatter() {
        // Test if setting a new formatter works
        LoggerFactory.setFormatter((event) -> event.getLevel() + " - " + event.getMessage());
        logger.info("This message should be formatted");
    }
}
