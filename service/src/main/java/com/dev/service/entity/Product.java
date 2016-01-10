package com.dev.service.entity;

import static java.lang.String.format;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
public class Product extends BaseEntity {

    private static final long serialVersionUID = 2356639092459704148L;

    @Size(min = 1, max = 255)
    private String descirption;

    private double price;

    @Min(1)
    private int quantity;

    public Product() {
        quantity = 1;
    }

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

    @Override
    public String toString() {
        return super.toString() + format(" | description=%s", descirption);
    }
}
