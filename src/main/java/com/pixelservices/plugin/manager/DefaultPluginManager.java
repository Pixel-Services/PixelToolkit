package com.pixelservices.plugin.manager;

import com.pixelservices.plugin.descriptor.finder.PluginDescriptorFinder;
import com.pixelservices.plugin.descriptor.finder.YamlDescriptorFinder;
import com.pixelservices.plugin.impl.SimplePlugin;
import com.pixelservices.plugin.lifecycle.PluginState;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DefaultPluginManager extends AbstractPluginManager {
    public DefaultPluginManager() {
        this(Paths.get("plugins"));
    }

    public DefaultPluginManager(Path directory) {
        this(directory, new YamlDescriptorFinder());
    }

    public DefaultPluginManager(Path directory, PluginDescriptorFinder descriptorFinder) {
        super(directory, descriptorFinder);
    }

    public void start() {
        getPlugins().forEach(pluginWrapper -> {
            if (pluginWrapper.getState().equals(PluginState.LOADED)) {
                ((SimplePlugin) pluginWrapper.getPlugin()).start();
            }
        });
    }

    public void stop() {
        getPlugins().forEach(pluginWrapper -> {
            if (pluginWrapper.getState().equals(PluginState.LOADED)) {
                ((SimplePlugin) pluginWrapper.getPlugin()).stop();
            }
        });
    }
}
