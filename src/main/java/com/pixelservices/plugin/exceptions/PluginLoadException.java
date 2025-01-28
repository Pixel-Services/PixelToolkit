package com.pixelservices.plugin.exceptions;

public class PluginLoadException extends PluginException {
    public PluginLoadException(String message, Exception e) {
        super(message, e);
    }
}
