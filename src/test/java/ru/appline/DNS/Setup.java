package ru.appline.DNS;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Setup {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/webdrivers/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get("https://www.dns-shop.ru/");
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
