package com.pixelservices.plugin.loader;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;

public class CustomClassLoader extends URLClassLoader {

    public CustomClassLoader(Path jarPath, ClassLoader parent) throws IOException {
        super(new URL[]{jarPath.toUri().toURL()}, parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }
}