package ru.aplana.cucumberallure;

public class Product {
    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String toString() {
        String res = "";
        res = res.concat(this.getName());
        res = res.concat("\n");
        res = res.concat(Integer.toString(this.getPrice()));
        res = res.concat("\n");
        return res;
    }
}
