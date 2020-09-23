package ru.appline.DNS.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.DNS.Entities.ProductList;
import ru.appline.DNS.managers.DriverManager;
import ru.appline.DNS.managers.ManagerPages;


public class SearchPage{

    @FindBy(xpath = "//input[@placeholder='Поиск по сайту']")
    private WebElement searchPanel;

    @FindBy(xpath = "//span[@class='cart-link__price']")
    private WebElement cart;

    WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 10);

    protected ManagerPages app = ManagerPages.getManagerPages();

    public SearchPage(){
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

    public ResultsPage searchList(String product){

        searchPanel.sendKeys(product);
        searchPanel.sendKeys(Keys.ENTER);
        return app.getResultsPage();
    }

    public ProductCard search(String product){

        searchPanel.sendKeys(product);
        searchPanel.sendKeys(Keys.ENTER);
        return app.getProductCard();
    }

    public SearchPage checkPrice(){
        waitUntilElementToBeVisibilityOf(cart);
        int cartPrice = Integer.parseInt(cart.getText().replaceAll(" |₽",""));
        Assert.assertEquals("Проверка карзины",
                cartPrice,
                ProductList.sumPrice());
        return this;
    }

    public CartPage clickCart(){
        cart.findElement(By.xpath("./..")).click();
        return app.getCartPage();
    }
}
