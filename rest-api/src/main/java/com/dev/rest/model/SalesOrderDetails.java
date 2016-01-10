package com.dev.rest.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SalesOrderDetails extends BaseDto {

    public static class OrderedProductDetails implements Serializable {

        private static final long serialVersionUID = -4305020715715132256L;

        private double price;

        private String productCode;

        private int quantity;

        private double total;

        public double getPrice() {
            return price;
        }

        public String getProductCode() {
            return productCode;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getTotal() {
            return total;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void setTotal(double total) {
            this.total = total;
        }
    }

    private static final long serialVersionUID = 144984440024439660L;

    private String customerCode;

    private Set<OrderedProductDetails> orderedProductsDetails;

    private double total;

    public SalesOrderDetails() {
        orderedProductsDetails = new HashSet<>();
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public Set<OrderedProductDetails> getOrderedProductsDetails() {
        return orderedProductsDetails;
    }

    public double getTotal() {
        return total;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public void setOrderedProductsDetails(Set<OrderedProductDetails> orderedProductsDetails) {
        this.orderedProductsDetails = orderedProductsDetails;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
