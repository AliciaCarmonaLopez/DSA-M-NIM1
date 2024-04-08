package edu.upc.dsa.models;

public class Product {
    private String productId;
    private String name;
    public int sales;
    private double price;

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public Product(String productId, String name, double price){
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.sales = 0;
    }
    public Product(){

    }
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
