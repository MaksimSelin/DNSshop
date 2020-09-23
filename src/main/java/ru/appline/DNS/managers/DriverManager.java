package ru.appline.DNS.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.appline.DNS.utils.PropConst;

/**
 * Класс WebDriver. Управляет драйвером. Синглтон.
 */
public class DriverManager {

    private static WebDriver driver;
    protected static PropManager propManager = PropManager.getPropManager();

    private DriverManager() {

    }

    /**
     * @return Возвращает WebDriver
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", propManager.getProperty(PropConst.PATH_CHROME_DRIVER));
            driver = new ChromeDriver();
        }
        return driver;
    }

    /**
     * Закрывает драйвер
     */
    public static void quitDriver() {
        driver.quit();
        driver = null;
    }
}
