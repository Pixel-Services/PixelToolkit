package com.pixelservices.plugin;

import com.pixelservices.logger.Logger;
import com.pixelservices.logger.LoggerFactory;
import com.pixelservices.plugin.descriptor.PluginDescriptor;
import org.jetbrains.annotations.NotNull;

public abstract class Plugin {
    private final PluginWrapper pluginWrapper;
    private final PluginDescriptor pluginDescriptor;
    private final Logger logger;

    public Plugin() {
        throw new UnsupportedOperationException("Plugin must be created with PluginWrapper and PluginDescriptor");
    }

    public Plugin(@NotNull PluginWrapper pluginWrapper, @NotNull PluginDescriptor pluginDescriptor) {
        this.pluginWrapper = pluginWrapper;
        this.pluginDescriptor = pluginDescriptor;
        logger = LoggerFactory.getLogger(pluginDescriptor.getPluginId());
    }

    public PluginWrapper getPluginWrapper() {
        return pluginWrapper;
    }

    public final PluginDescriptor getMetaData(){
        return pluginDescriptor;
    }

    public final Logger getLogger() {
        return logger;
    }
}
