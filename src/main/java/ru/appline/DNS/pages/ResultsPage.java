package ru.appline.DNS.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ResultsPage extends SearchPage{

    @FindBy(xpath = "//a[@class='ui-link']")
    private List<WebElement> listWebElements;


    public ProductCard insertElement(String str){
        for (WebElement elem : listWebElements){
            if (elem.getText().toLowerCase().contains(str.toLowerCase())) {
                scrollToElement(elem);
                elem.click();
            }
            break;
        }
        return app.getProductCard();
    }
}
