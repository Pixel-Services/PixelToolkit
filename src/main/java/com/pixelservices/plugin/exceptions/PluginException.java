package com.pixelservices.plugin.exceptions;


public class PluginException extends Exception {
    public PluginException(String message, Exception e) {
        super(message, e);
    }
}