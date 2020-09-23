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
import ru.appline.DNS.entities.ProductList;
import ru.appline.DNS.managers.DriverManager;
import ru.appline.DNS.managers.ManagerPages;

/**
 * Шапка всех PageObject. Является суперклассом.
 */
public class SearchPage {

    /**
     * Панель поиска
     */
    @FindBy(xpath = "//input[@placeholder='Поиск по сайту']")
    private WebElement searchPanel;

    /**
     * карзина
     */
    @FindBy(xpath = "//span[@class='cart-link__price']")
    private WebElement cart;

    WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 10);

    protected ManagerPages app = ManagerPages.getManagerPages();

    public SearchPage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    /**
     * скрол до элемента
     * @param webElement - сам элемента
     */
    protected void scrollToElement(WebElement webElement) {
        // ((JavascriptExecutor)DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView();", webElement);
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript(
                "window.scroll(" + (webElement.getLocation().x + 0) + ","
                        + (webElement.getLocation().y - 200) + ");", webElement, 0, -200);
    }

    /**
     * ждет элемент, пока тот не станет кликабельным.
     * @param webElement - сам элемент
     */
    protected void waitUntilElementToBeClickable(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /**
     * ждет появления элемента
     * @param element - сам элемент
     */
    protected void waitUntilElementToBeVisibilityOf(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * поиск продуктов
     * @param product - название продукта
     * @return - водвращает страницу с списком продуктов
     */
    public ResultsPage searchList(String product) {

        searchPanel.sendKeys(product);
        searchPanel.sendKeys(Keys.ENTER);
        return app.getResultsPage();
    }

    /**
     * поиск продукта
     * @param product - название продукта
     * @return возвращает один продукт
     */
    public ProductCardPage search(String product) {

        searchPanel.sendKeys(product);
        searchPanel.sendKeys(Keys.ENTER);
        return app.getProductCard();
    }

    /**
     * проверка карзины
     * @return
     */
    public SearchPage checkPrice() {
        waitUntilElementToBeVisibilityOf(cart);
        int cartPrice = Integer.parseInt(cart.getText().replaceAll(" |₽", ""));
        Assert.assertEquals("Проверка карзины",
                cartPrice,
                ProductList.sumPrice());
        return this;
    }

    /**
     * переход в карзину
     * @return
     */
    public CartPage clickCart() {
        cart.findElement(By.xpath("./..")).click();
        return app.getCartPage();
    }
}
