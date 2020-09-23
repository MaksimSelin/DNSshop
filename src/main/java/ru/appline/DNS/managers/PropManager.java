package ru.appline.DNS.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropManager {

    private final Properties properties = new Properties();

    private static PropManager propManager;

    private PropManager(){
        try {
            properties.load(new FileInputStream(new File("src/main/resources/"+
                    System.getProperty("env", "property") +".property")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
