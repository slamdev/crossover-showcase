package com.dev.rest.model;

public class SalesOrderRow extends BaseDto {

    private static final long serialVersionUID = 144984440024439660L;

    private String customerName;

    private double totalPrice;

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
