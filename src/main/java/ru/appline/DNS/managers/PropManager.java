package ru.appline.DNS.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Обработка файла с настройками
 */
public class PropManager {

    private final Properties properties = new Properties();

    private static PropManager propManager;

    private PropManager() {
        try {
            properties.load(new FileInputStream(new File("src/main/resources/" +
                    System.getProperty("env", "property") + ".properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropManager getPropManager() {
        if (propManager == null)
            propManager = new PropManager();
        return propManager;
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
