package ru.appline.DNS.Entities;

import java.util.Objects;

public class Product {
    private String code;
    private String price;
    private String description;
    private Boolean warranty;
    private String priceWithoutWarranty;
    private int count;

    public Product(String code, String price, String description) {
        this.code = code;
        this.price = price;
        this.description = description;
        this.warranty = false;
        count = 1;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getWarranty() {
        return warranty;
    }

    public void setWarranty(Boolean warranty) {
        this.warranty = warranty;
    }

    public String getPriceWithoutWarranty() {
        return priceWithoutWarranty;
    }

    public void setPriceWithoutWarranty(String priceWithWarranty) {
        this.priceWithoutWarranty = priceWithWarranty;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (count<0)
            count = 0;
        this.count = count;
    }

    public int getFullPrice(){
        return Integer.parseInt(price.replaceAll(" |₽","")) * count;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(code, product.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
