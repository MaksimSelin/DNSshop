package ru.appline.DNS.pages;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.DNS.managers.DriverManager;

public class BasePage {

    WebDriverWait  wait = new WebDriverWait(DriverManager.getDriver(), 10);

    public BasePage(){
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    protected void scrollToElement(WebElement webElement){
       // ((JavascriptExecutor)DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView();", webElement);
        ((JavascriptExecutor)DriverManager.getDriver()).executeScript(
                "window.scroll(" + (webElement.getLocation().x + 0) + ","
                        + (webElement.getLocation().y - 200) +");", webElement, 0, -200);
    }

    protected void waitUntilElementToBeClickable(WebElement webElement){
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected void waitUntilElementToBeVisibilityOf(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }


}
