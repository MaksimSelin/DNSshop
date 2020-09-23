package ru.appline.DNS.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import ru.appline.DNS.entities.Product;
import ru.appline.DNS.entities.ProductList;
import ru.appline.DNS.managers.DriverManager;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CartPage extends SearchPage{

    @FindBy(xpath = "//div[@class='cart-items__product-code']/div")
    List<WebElement> list;

    @FindBy(xpath = "//div[@class='total-amount-block total-amount__info-block']//span[@class='price__current']")
    WebElement infoElement;


    public CartPage checkWarranty(){
        for (Product product : ProductList.getList()){
            if (product.getWarranty()) {
                for (WebElement element : list) {
                    if (product.getCode().equals(element.getText())) {
                        assertEquals("Check warranty", DriverManager.getDriver().findElement(By.xpath("//span[contains(text(), '24')]")).getAttribute("class"),
                                "base-ui-radio-button__icon base-ui-radio-button__icon_checked");
                    }
                }
            }
        }
        return this;
    }

    public CartPage checkPrice(){
        for (Product product : ProductList.getList()){
            for (WebElement element : list){
                scrollToElement(element);
                waitUntilElementToBeVisibilityOf(element);
                if (product.getCode().equals(element.getText())){
                    waitUntilElementToBeVisibilityOf(element);
                    assertEquals("Check price", element.findElement(By.xpath("./../../..//span[@class='price__current']"))
                                    .getText().replaceAll(" |₽",""),
                            product.getPriceWithoutWarranty().replaceAll(" |₽",""));
                }
            }
        }
    assertEquals("Check end price", Integer.parseInt(infoElement.getText().replaceAll(" |₽","")),
            ProductList.sumPrice());
        return this;
    }

    public CartPage remove(String str){
        for (WebElement element : list){
            if (element.findElement(By.xpath("./../../div[@class='cart-items__product-name']")).getText()
                    .toLowerCase().contains(str.toLowerCase())){
                for (Product productElement : ProductList.getList())
                    if (productElement.getCode().equals(element.getText()))
                        productElement.setCount(productElement.getCount() - 1);

                scrollToElement(element.findElement(By.xpath("./../../..//button[contains(@class, 'minus')]")));
                element.findElement(By.xpath("./../../..//button[contains(@class, 'minus')]")).click();

            }
        }

        int size = list.size();
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return list.size() != size;
            }
        });

        checkPrice();
        return this;
    }

    public CartPage addCountProduct(String product, int count){
        for (WebElement element : list){
            if (element.findElement(By.xpath("./../../div[@class='cart-items__product-name']")).getText()
                    .toLowerCase().contains(product.toLowerCase())) {

                scrollToElement(element);
                for (int i = 0; i<count; i++){
                    try {
                        waitUntilElementToBeClickable(element.findElement(By.xpath("./../../..//button[contains(@class, 'plus')]")));
                        String param = element.findElement(By.xpath("./../../..//input[@class='count-buttons__input']")).getAttribute("value");
                        element.findElement(By.xpath("./../../..//button[contains(@class, 'plus')]")).click();
                        wait.until(new ExpectedCondition<Boolean>() {
                            public Boolean apply(WebDriver driver) {
                                return !param.equals(element
                                        .findElement(By.xpath("./../../..//input[@class='count-buttons__input']"))
                                        .getAttribute("value"));
                            }

                        });
                        for (Product productElement : ProductList.getList())
                            if (productElement.getCode().equals(element.getText()))
                            productElement.setCount(productElement.getCount()+1);
                    }
                    catch (TimeoutException e){
                        break;
                    }
                }
            }
        }
        checkFullPrice();
        return this;
    }

    private void checkFullPrice(){
        assertEquals("Check full price", ProductList.sumPrice(),
                Integer.parseInt(infoElement.getText().replaceAll(" |₽","")));
    }


    public CartPage restoreLastRemoved(){
        WebElement restore = DriverManager.getDriver().findElement(By.xpath("//span[@class='restore-last-removed']"));
        restore.click();

        for (WebElement element : list){
            for (Product product : ProductList.getList()){
                if (element.equals(product.getCode()) && product.getCount()==0 ){
                    product.setCount(1);
                }
            }
        }
        checkFullPrice();
        return this;
    }



}
