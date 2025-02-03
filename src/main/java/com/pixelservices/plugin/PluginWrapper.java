package com.pixelservices.plugin;

import com.pixelservices.plugin.descriptor.PluginDescriptor;
import com.pixelservices.plugin.exceptions.PluginLoadException;
import com.pixelservices.plugin.lifecycle.PluginState;
import com.pixelservices.plugin.manager.PluginManager;

import java.nio.file.Path;

public class PluginWrapper {
    private final PluginDescriptor pluginDescriptor;
    private final Path path;
    private final PluginManager pluginManager;
    private Plugin plugin;
    private PluginState state = PluginState.CREATED;

    public PluginWrapper(PluginManager pluginManager, PluginDescriptor pluginDescriptor, Path path) {
        this.pluginManager = pluginManager;
        this.pluginDescriptor = pluginDescriptor;
        this.path = path;
    }

    public void load() throws PluginLoadException {
        try {
            this.plugin = PluginFactory.createPlugin(path, pluginDescriptor);
            plugin.load(this, pluginDescriptor);
            state = PluginState.LOADED;
        } catch (Exception e) {
            state = PluginState.FAILED;
            throw new PluginLoadException(e.getMessage(), e);
        }
    }

    public void unload() {
        plugin = null;
        state = PluginState.UNLOADED;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public PluginDescriptor getPluginDescriptor() {
        return pluginDescriptor;
    }

    public PluginState getState() {
        return state;
    }

    public void setState(PluginState state) {
        this.state = state;
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }
}