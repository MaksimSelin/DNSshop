package ru.appline.DNS.managers;

import ru.appline.DNS.pages.CartPage;
import ru.appline.DNS.pages.ProductCardPage;
import ru.appline.DNS.pages.ResultsPage;
import ru.appline.DNS.pages.SearchPage;

public class ManagerPages {

    private static ManagerPages managerPages;

    private CartPage cartPage;
    private ProductCardPage productCard;
    private ResultsPage resultsPage;
    private SearchPage searchPage;

    /**
     * Мэнеджер PageObject. Возвращает все страницы.
     */
    private ManagerPages() {

    }

    public static ManagerPages getManagerPages() {
        if (managerPages == null)
            managerPages = new ManagerPages();
        return managerPages;
    }

    public CartPage getCartPage() {
        if (cartPage == null)
            cartPage = new CartPage();
        return cartPage;
    }

    public ProductCardPage getProductCard() {
        if (productCard == null)
            productCard = new ProductCardPage();
        return productCard;
    }

    public ResultsPage getResultsPage() {
        if (resultsPage == null)
            resultsPage = new ResultsPage();
        return resultsPage;
    }

    public SearchPage getSearchPage() {
        if (searchPage == null)
            searchPage = new SearchPage();
        return searchPage;
    }
}
