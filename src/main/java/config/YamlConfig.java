package config;

import org.simpleyaml.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * The YamlConfig class provides utility methods for loading, saving, and managing YAML configuration files.
 */
public class YamlConfig extends YamlConfiguration {
    private final File file;

    /**
     * Constructs a YamlConfig instance of the default configuration file.
     */
    public YamlConfig() {
        this("config.yml");
    }

    /**
     * Constructs a YamlConfig instance with a specified file path.
     *
     * @param path the path to the configuration file.
     */
    public YamlConfig(String path) {
        this(getFile(path));
    }

    /**
     * Constructs a YamlConfig instance with a specified file.
     *
     * @param file the configuration file.
     */
    private YamlConfig(File file) {
        super(loadConfig(file));
        this.file = file;
    }

    /**
     * Loads a YamlConfiguration from a file.
     *
     * @param file the configuration file to load
     * @return the loaded YamlConfiguration
     */
    private static YamlConfiguration loadConfig(File file) {
        try {
            return YamlConfiguration.loadConfiguration(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the configuration file.
     *
     * @param path the path to the configuration file.
     * @return the configuration file
     */
    private static File getFile(String path) {
        File file = new File(path);
        try {
            if (!file.exists()) {
                try (InputStream resourceStream = YamlConfig.class.getClassLoader().getResourceAsStream(file.getName())) {
                    if (resourceStream != null) {
                        Files.copy(resourceStream, file.toPath());
                    } else {
                        file.createNewFile();
                    }
                }
            }
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves the configuration file to disk.
     */
    public void save() {
        try {
            save(file);
        } catch (IOException ignored) {

        }
    }
}
