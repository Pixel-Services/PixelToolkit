package com.pixelservices.plugin.descriptor;

import com.pixelservices.plugin.depedency.PluginDependency;

import java.util.ArrayList;
import java.util.List;

public class DefaultPluginDescriptor implements PluginDescriptor {
    private String pluginId;
    private String description;
    private String version;
    private String pluginClass;
    private List<String> authors;
    private String license;
    private List<PluginDependency> dependencies;

    public DefaultPluginDescriptor() {
        dependencies = new ArrayList<>();
    }

    public DefaultPluginDescriptor(String pluginId, String description, String version, String pluginClass, List<String> authors, String license) {
        this.pluginId = pluginId;
        this.description = description;
        this.version = version;
        this.pluginClass = pluginClass;
        this.authors = authors;
        this.license = license;
    }

    public void addDependency(PluginDependency dependency) {
        this.dependencies.add(dependency);
    }

    @Override
    public String getPluginId() {
        return pluginId;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public String getPluginClass() {
        return pluginClass;
    }

    @Override
    public List<String> getAuthors() {
        return authors;
    }

    @Override
    public String getLicense() {
        return license;
    }

    @Override
    public List<PluginDependency> getDependencies() {
        return dependencies;
    }
}
