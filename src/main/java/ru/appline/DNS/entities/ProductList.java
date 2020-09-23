package ru.appline.DNS.entities;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private static List<Product> productList;

    private ProductList(){

    }

    public static List<Product> getList(){
        if (productList == null){
            productList = new ArrayList<>();
        }
        return productList;
    }

    public static int sumPrice(){
        int sum = 0;
        for (Product product : productList){
            sum =Integer.parseInt(product.getPrice().replaceAll(" |₽",""))*product.getCount()+sum;
        }
        return sum;
    }
}
