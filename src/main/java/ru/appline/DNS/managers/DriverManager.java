package ru.appline.DNS.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.appline.DNS.utils.PropConst;

public class DriverManager {

    private static WebDriver driver;
    protected static PropManager propManager = PropManager.getPropManager();

    private DriverManager(){

    }

    public static WebDriver getDriver(){
        if (driver == null){
            System.setProperty("webdriver.chrome.driver", propManager.getProperty(PropConst.PATH_CHROME_DRIVER));
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static void quitDriver(){
        driver.quit();
        driver = null;
    }
}
