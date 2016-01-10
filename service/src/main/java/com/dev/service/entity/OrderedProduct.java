package com.dev.service.entity;

import static java.lang.String.format;
import static java.lang.System.identityHashCode;
import static java.util.Objects.hash;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Embeddable
public class OrderedProduct {

    @ManyToOne(optional = false)
    @NotNull
    private Product product;

    @Min(1)
    private int quantity;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OrderedProduct other = (OrderedProduct) obj;
        if (product == null) {
            if (other.product != null) {
                return false;
            }
        } else if (!product.equals(other.product)) {
            return false;
        }
        if (quantity != other.quantity) {
            return false;
        }
        return true;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        return hash(product, quantity);
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return format("%s: hash=%s | product=%s | quantity=%s", getClass().getSimpleName(), identityHashCode(this),
                product, quantity);
    }
}
