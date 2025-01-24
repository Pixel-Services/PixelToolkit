package com.pixelservices;

import com.pixelservices.config.YamlConfig;
import com.pixelservices.logger.Logger;
import com.pixelservices.logger.LoggerFactory;
import org.junit.Test;
import java.io.File;

public class ConfigTest {
    private static final Logger logger = LoggerFactory.getLogger(ConfigTest.class);

    @Test
    public void testConfig() {
        String filePath = "config.yml";
        YamlConfig config = new YamlConfig(filePath);
        logger.info(config.getString("test"));

        // Clean up the file after the test
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
}