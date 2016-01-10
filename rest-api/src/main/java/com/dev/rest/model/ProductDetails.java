package com.dev.rest.model;

public class ProductDetails extends BaseDto {

    private static final long serialVersionUID = 3152509851530574737L;

    private String descirption;

    private double price;

    private int quantity;

    public String getDescirption() {
        return descirption;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setDescirption(String descirption) {
        this.descirption = descirption;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
