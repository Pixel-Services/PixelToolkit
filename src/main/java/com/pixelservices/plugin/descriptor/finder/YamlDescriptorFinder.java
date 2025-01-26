package com.pixelservices.plugin.descriptor.finder;

import com.pixelservices.logger.Logger;
import com.pixelservices.plugin.depedency.PluginDependency;
import com.pixelservices.plugin.descriptor.DefaultPluginDescriptor;
import com.pixelservices.plugin.descriptor.PluginDescriptor;
import org.simpleyaml.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class YamlDescriptorFinder implements PluginDescriptorFinder {
    private final String descriptorFileName;

    public YamlDescriptorFinder() {
        this("plugin.yml");
    }

    public YamlDescriptorFinder(String descriptorFileName) {
        this.descriptorFileName = descriptorFileName;
    }

    public PluginDescriptor findPluginDescriptor(Path path) {
        YamlConfiguration yamlConfig = new YamlConfiguration();
        try (JarFile jarFile = new JarFile(path.toFile())) {
            JarEntry entry = jarFile.getJarEntry(descriptorFileName);
            if (entry != null) {
                try (InputStream inputStream = jarFile.getInputStream(entry)) {
                    yamlConfig.load(inputStream);
                }
            } else {
                return null;
            }
        } catch (IOException ignored) {
            return null;
        }
        String pluginId = yamlConfig.getString("pluginId");
        String description = yamlConfig.getString("description");
        String version = yamlConfig.getString("version");
        String pluginClass = yamlConfig.getString("main");
        List<String> authors = yamlConfig.getStringList("authors");
        String license = yamlConfig.getString("license");
        DefaultPluginDescriptor descriptor = new DefaultPluginDescriptor(pluginId, description, version, pluginClass, authors, license);
        for (String dependency : yamlConfig.getConfigurationSection("dependencies").getKeys(false)) {
            descriptor.addDependency(new PluginDependency(dependency, yamlConfig.getBoolean("dependencies." + dependency)));
        }
        return descriptor;
    }
}
