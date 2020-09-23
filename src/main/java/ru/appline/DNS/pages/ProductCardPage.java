package ru.appline.DNS.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import ru.appline.DNS.entities.Product;
import ru.appline.DNS.entities.ProductList;
import ru.appline.DNS.managers.DriverManager;

import java.util.List;

/**
 * Страница с описание продукта
 */
public class ProductCardPage extends SearchPage {

    private Product product;

    /**
     * цена продукта
     */
    @FindBy(xpath = "//span[contains(@class,'product-card-price__current')]")
    private WebElement price;

    /**
     * код продукта
     */
    @FindBy(xpath = "//span[@data-product-param='code']")
    private WebElement code;

    /**
     * описание
     */
    @FindBy(xpath = "//div[@class='price-item-description']/p")
    private WebElement description;

    /**
     *страховка
     */
    @FindBy(xpath = "//select[@class='form-control select']")
    private List<WebElement> list;

    /**
     * кнопка купить
     */
    @FindBy(xpath = "//button[text()='Купить']")
    private WebElement button;

    /**
     * генерация продукта
     */
    private void scan() {
        waitUntilElementToBeVisibilityOf(code);
        waitUntilElementToBeVisibilityOf(price);
        waitUntilElementToBeVisibilityOf(description);
        product = new Product(code.getText(), price.getText(), description.getText());
    }

    /**
     * установка гарантии
     * @param val - значение гарантии ( смотреть на странице в селекторе )
     * @return
     */
    public ProductCardPage setWarranty(String val) {
        if (list.size() > 0) {
            scan();
            Select select = new Select(list.get(0));
            select.selectByValue(val);
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return !product.getPrice().equals(price.getText());
                }
            });
            product.setWarranty(true);
            addToList(product);
        }
        return this;
    }

    /**
     * нажать купить
     * @return
     */
    public ProductCardPage buy() {
        scan();
        product.setPriceWithoutWarranty(price.getText());
        addToList(product);
        if (isElementPresent()) {
            String cartPrice = DriverManager.getDriver()
                    .findElement(By.xpath("//span[@class='cart-link__price']")).getText();
            button.click();
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return !cartPrice.equals(DriverManager.getDriver()
                            .findElement(By.xpath("//span[@class='cart-link__price']")).getText());
                }
            });
        } else button.click();
        return this;
    }

    /**
     * внутренний метод для сохранения продукта
     * @param product
     */
    private void addToList(Product product) {
        if (ProductList.getList().contains(product)) {
            for (Product prod : ProductList.getList()) {
                if (prod.equals(product)) {
                    product.setPriceWithoutWarranty(prod.getPrice());
                    if (prod.getWarranty()) {
                        product.setWarranty(true);
                    }
                }
            }
            ProductList.getList().remove(product);
        }
        ProductList.getList().add(product);
    }

    /**
     * проверка наличия элемента
     * @return
     */
    private boolean isElementPresent() {
        try {
            DriverManager.getDriver()
                    .findElement(By.xpath("//span[@class='cart-link__price']"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}
