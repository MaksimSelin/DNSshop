package ru.appline.DNS.managers;

import ru.appline.DNS.utils.PropConst;

import java.util.concurrent.TimeUnit;

public class InitManager {

    private static PropManager propManager = PropManager.getPropManager();

    public static void initFramework(){
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(propManager.getProperty(PropConst.IMPLICITLY_WAIT)) , TimeUnit.SECONDS);
        DriverManager.getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(propManager.getProperty(PropConst.PAGE_LOAD_TIMEOUT)), TimeUnit.SECONDS);
    }

    public static void quitFramework(){
        DriverManager.quitDriver();
    }
}
