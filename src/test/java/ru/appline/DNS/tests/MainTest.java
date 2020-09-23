package ru.appline.DNS.tests;

import org.junit.Test;


public class MainTest extends Setup {

    @Test
    public void scenario(){


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
