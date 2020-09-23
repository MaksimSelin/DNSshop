package ru.appline.DNS.tests;

import org.junit.Test;
import ru.appline.DNS.pages.CartPage;
import ru.appline.DNS.pages.ProductCard;
import ru.appline.DNS.pages.ResultsPage;
import ru.appline.DNS.pages.SearchPage;


public class MainTest extends Setup{

    @Test
    public void scenario() throws InterruptedException {





        app.getSearchPage().searchList("playstation")
                .insertElement("playstation 4 slim black")
                .setWarranty("2")
                .buy()
                .search("Detroit")
                .buy()
                .checkPrice()
                .clickCart()
                .checkWarranty()
                .checkPrice()
                .remove("detroit")
                .addCountProduct("playstation", 3)
                .restoreLastRemoved();


    }
}
