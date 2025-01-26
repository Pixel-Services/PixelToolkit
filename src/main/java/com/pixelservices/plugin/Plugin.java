package com.pixelservices.plugin;

import com.pixelservices.logger.Logger;
import com.pixelservices.logger.LoggerFactory;
import com.pixelservices.plugin.descriptor.PluginDescriptor;

public abstract class Plugin {
    private PluginDescriptor pluginDescriptor;
    private Logger logger;

    public final void load(PluginDescriptor pluginDescriptor) {
        this.pluginDescriptor = pluginDescriptor;
        logger = LoggerFactory.getLogger(pluginDescriptor.getPluginId());
    }

    public final PluginDescriptor getMetaData(){
        return pluginDescriptor;
    }

    public final Logger getLogger() {
        return logger;
    }
}
