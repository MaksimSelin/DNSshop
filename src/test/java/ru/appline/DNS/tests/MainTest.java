package ru.appline.DNS.tests;

import org.junit.Test;
import ru.appline.DNS.pages.CartPage;
import ru.appline.DNS.pages.ProductCard;
import ru.appline.DNS.pages.ResultsPage;
import ru.appline.DNS.pages.SearchPage;


public class MainTest extends Setup{

    @Test
    public void scenario() throws InterruptedException {

        SearchPage searchPage = new SearchPage();
        ResultsPage resultsPage = new ResultsPage();
        ProductCard productCard = new ProductCard();
        CartPage cartPage = new CartPage();

        searchPage.search("playstation");
        resultsPage.insertElement("playstation 4 slim black");
        productCard.setWarranty("2")
                .buy()
                .search("Detroit");
        productCard.buy();
        searchPage.checkPrice();
        searchPage.clickCart();
        cartPage.checkWarranty();
        cartPage.checkPrice();
        cartPage.remove("detroit");
        cartPage.addCountProduct("playstation", 3);
        cartPage.restoreLastRemoved();


        Thread.sleep(5000);
        //searchPage.search("Detroit");

    }
}
