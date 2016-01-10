package com.dev.service.entity;

import static java.lang.String.format;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class SalesOrder extends BaseEntity {

    private static final long serialVersionUID = 2356639092459704148L;

    @NotNull
    @OneToOne
    private Customer customer;

    @ElementCollection
    @NotEmpty
    private Set<OrderedProduct> orderedProducts;

    public SalesOrder() {
        orderedProducts = new HashSet<>();
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrderedProducts(Set<OrderedProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    @Override
    public String toString() {
        return super.toString() + format(" | customer=%s | products=%s", customer, orderedProducts);
    }
}
