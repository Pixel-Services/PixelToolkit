package com.pixelservices.plugin;

import com.pixelservices.logger.Logger;
import com.pixelservices.logger.LoggerFactory;
import com.pixelservices.plugin.descriptor.PluginDescriptor;
import com.pixelservices.plugin.loader.CustomClassLoader;

import java.nio.file.Path;

public class PluginFactory {
    private static final Logger logger = LoggerFactory.getLogger(PluginFactory.class);

    public static Plugin createPlugin(Path path, PluginDescriptor descriptor) {
        try {
            CustomClassLoader classLoader = new CustomClassLoader(path, PluginFactory.class.getClassLoader());
            Class<?> pluginClass = classLoader.loadClass(descriptor.getPluginClass());
            return (Plugin) pluginClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            logger.error("Failed to create plugin", e);
            return null;
        }
    }
}