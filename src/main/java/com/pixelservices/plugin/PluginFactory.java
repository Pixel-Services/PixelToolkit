package com.pixelservices.plugin;

import com.pixelservices.plugin.descriptor.PluginDescriptor;
import com.pixelservices.plugin.exceptions.PluginLoadException;
import com.pixelservices.plugin.loader.CustomClassLoader;
import com.pixelservices.plugin.manager.PluginManager;

import java.nio.file.Path;

public class PluginFactory {
    public static Plugin createPlugin(Path path, PluginWrapper pluginWrapper, PluginDescriptor descriptor) throws PluginLoadException {
        try (CustomClassLoader classLoader = new CustomClassLoader(path, PluginFactory.class.getClassLoader())) {
            Class<?> pluginClass = classLoader.loadClass(descriptor.getPluginClass());
            return (Plugin) pluginClass.getDeclaredConstructor(PluginWrapper.class, PluginDescriptor.class).newInstance(pluginWrapper, descriptor);
        } catch (Exception e) {
            throw new PluginLoadException("Failed to create plugin", e);
        }
    }
}