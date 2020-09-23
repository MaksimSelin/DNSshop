package ru.appline.DNS.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.DNS.managers.DriverManager;

import java.util.concurrent.TimeUnit;

public class Setup {


    @Before
    public void setup(){
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
        DriverManager.getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        DriverManager.getDriver().get("https://www.dns-shop.ru/");
    }

    @After
    public void tearDown(){
        DriverManager.quitDriver();
    }
}
