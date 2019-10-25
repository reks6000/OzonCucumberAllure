package ru.aplana.cucumberallure;

import org.junit.Assert;

import java.util.ArrayList;

public class Cart {
    ArrayList<Product> products = new ArrayList<>();

    public void add(Product item) {
        products.add(item);
    }

    public void checkProduct (String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return;
            }
        }
        Assert.fail(String.format("В корзине неопознанный товар %s", name));
    }

    public String toString() {
        String res = "";
        Product maxCostProduct = products.get(0);
        for (Product product : products) {
            res = res.concat(product.toString());
            if (product.getPrice() > maxCostProduct.getPrice()) {
                maxCostProduct = product;
            }
        }
        res = res.concat("Max cost:\n");
        res = res.concat(maxCostProduct.toString());
        return res;
    }
}
