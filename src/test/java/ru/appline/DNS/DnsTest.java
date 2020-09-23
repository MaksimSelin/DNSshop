package ru.appline.DNS;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DnsTest extends Setup{
    public String price;
    public String priceWithWarranty;
    public String productPrice;
    int sum;
    public String cartPrice;

    @Test
    public void scenario() throws InterruptedException {
        WebElement search = driver.findElement(By.xpath("//input[@placeholder='Поиск по сайту']"));
        search.sendKeys("playstation");
        search.sendKeys(Keys.ENTER);


        List <WebElement> listElements = driver.findElements(By.xpath("//a[@class='ui-link']"));

        for (WebElement elem : listElements){
            if (elem.getText().toLowerCase().contains("playstation 4 slim black"))
            elem.click();
            break;
        }

        WebElement priceElement = driver.findElement(By.xpath("//span[@class='product-card-price__current']"));
        wait.until(ExpectedConditions.visibilityOf(priceElement));
        price = priceElement.getText();

        Select select = new Select(driver.findElement(By.xpath("//select[@class='form-control select']")));
        select.selectByValue("2");

        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ! price.equals(priceElement.getText());
            }
        });

        priceWithWarranty = priceElement.getText();
        driver.findElement(By.xpath("//button[text()='Купить']")).click();

        search = driver.findElement(By.xpath("//input[@placeholder='Поиск по сайту']"));
        search.sendKeys("Detroit");
        search.sendKeys(Keys.ENTER);

        WebElement cart = driver.findElement(By.xpath("//span[@class='cart-link__price']"));
        cartPrice = cart.getText();
        WebElement priceElement1 = driver.findElement(By.xpath("//span[@class='product-card-price__current']"));
        productPrice = priceElement1.getText();
        driver.findElement(By.xpath("//button[text()='Купить']")).click();

        Thread.sleep(1000);
        cart = driver.findElement(By.xpath("//span[@class='cart-link__price']"));
        WebElement finalCart = cart;

        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ! cartPrice.equals(finalCart.getText());
            }
        });

        sum = Integer.parseInt(priceWithWarranty.replaceAll(" |₽",""))+
                Integer.parseInt(productPrice.replaceAll(" |₽",""));


        assertEquals("Check cart", sum, Integer.parseInt(cart.getText().replaceAll(" |₽","")));

        cart.click();

        assertEquals("Check warranty" , driver.findElement(By.xpath("//span[contains(text(), '24')]")).getAttribute("class"),
                "base-ui-radio-button__icon base-ui-radio-button__icon_checked");



        Thread.sleep(5000);



    }
}
