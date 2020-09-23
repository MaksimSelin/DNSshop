package ru.appline.DNS.managers;

import ru.appline.DNS.pages.CartPage;
import ru.appline.DNS.pages.ProductCard;
import ru.appline.DNS.pages.ResultsPage;
import ru.appline.DNS.pages.SearchPage;

public class ManagerPages {

    private static ManagerPages managerPages;

    private CartPage cartPage;
    private ProductCard productCard;
    private ResultsPage resultsPage;
    private SearchPage searchPage;

    private ManagerPages(){

    }

    public static ManagerPages getManagerPages(){
        if (managerPages == null)
            managerPages = new ManagerPages();
        return managerPages;
    }

    public CartPage getCartPage(){
        if (cartPage == null)
            cartPage = new CartPage();
        return cartPage;
    }

    public ProductCard getProductCard(){
        if (productCard == null)
            productCard = new ProductCard();
        return productCard;
    }

    public ResultsPage getResultsPage(){
        if (resultsPage == null)
            resultsPage = new ResultsPage();
        return resultsPage;
    }

    public SearchPage getSearchPage(){
        if (searchPage == null)
            searchPage = new SearchPage();
        return searchPage;
    }
}
