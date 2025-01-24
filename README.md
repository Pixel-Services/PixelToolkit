# PixelToolkit (PTK) 🧰
PixelToolkit (PTK) is a Java API that provides essential utilities, wrappers, 
and foundational components used across all Pixel Services projects.

## Features

- **Logging**: A flexible logging framework with support for different log levels, custom formatters, and event listeners.
- **Configuration**: Easy-to-use configuration management with support for YAML files.
- **Utilities**: Various utility classes and methods to simplify common tasks.

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven

## Installation
To include Flash in your project, add the following dependency and repository to your ``pom.xml``:

### Dependency
```xml
<dependency>
    <groupId>com.pixelservices</groupId>
    <artifactId>pixeltoolkit</artifactId>
    <version>${ptkversion}</version>
</dependency>
```
### Repository
```xml
<repository>
    <id>pixel-services-releases</id>
    <name>Pixel Services</name>
    <url>https://maven.pixel-services.com/releases</url>
</repository>
```

## Usage
### Logging
Logging
Create a logger instance and use it to log messages at different levels, create Listeners to handle log events, and set a custom formatter:
```java
import com.pixelservices.logger.Logger;
import com.pixelservices.logger.LoggerFactory;

public class Example {
    private static final Logger logger = LoggerFactory.getLogger(Example.class);

    public static void main(String[] args) {
        logger.info("This is an info message");
        logger.warn("This is a warning message");
        logger.error("This is an error message");
        logger.debug("This is a debug message");
        
        // Create a listener to handle log events
        LoggerLogEventListener listener = event -> {
            if (event.getLevel() == Level.INFO){
                event.setCancelled(true);
                logger.debug("Event cancelled: " + event.getMessage());
            }
        };
        
        //Register the listener
        LoggerFactory.addListener(listener);
        
        // Set a custom formatter
        LoggerFactory.setFormatter((event) -> event.getLevel() + " - " + event.getMessage());
        logger.info("This message should be formatted");
        
    }
}
```

### Configuration
Load a configuration file and access/set its values:
```java
import com.pixelservices.config.YamlConfig;
import com.pixelservices.logger.Logger;
import com.pixelservices.logger.LoggerFactory;

public class Example {
    public static void main(String[] args) {
        // Load the configuration file
        YamlConfig config = new YamlConfig("config.yml");
        
        // Access a value
        String value = config.getString("key");
        
        // Set a value
        config.set("key", "new value");
        config.save();
    }
}
```

## Contributing
We welcome contributions! To contribute to PTK:
1. Fork the repository: [PixelToolkit on GitHub](https://github.com/Pixel-Services/PixelToolkit)
2. Create a feature branch: `git checkout -b feature-name`
3. Commit your changes: `git commit -m 'Add feature'`
4. Push to the branch: `git push origin feature-name`
5. Submit a pull request.