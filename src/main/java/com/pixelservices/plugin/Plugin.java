package com.pixelservices.plugin;

import com.pixelservices.logger.Logger;
import com.pixelservices.logger.LoggerFactory;
import com.pixelservices.plugin.descriptor.PluginDescriptor;

public abstract class Plugin {
    private PluginWrapper pluginWrapper;
    private PluginDescriptor pluginDescriptor;
    private Logger logger;

    final void load(PluginWrapper pluginWrapper, PluginDescriptor pluginDescriptor) {
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
