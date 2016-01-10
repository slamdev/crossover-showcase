package com.dev.service.boundary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.service.entity.Customer;
import com.dev.service.entity.OrderedProduct;
import com.dev.service.entity.Product;
import com.dev.service.entity.SalesOrder;

@Service
public class OrderService {

    private static void validateQuantity(OrderedProduct orderedProduct) {
        if (orderedProduct.getQuantity() > orderedProduct.getProduct().getQuantity()) {
            throw new IllegalArgumentException("Out of stock");
        }
    }

    @Autowired
    private SalesOrderRepository orders;

    @Autowired
    private ProductRepository products;

    public double calculateCredit(Customer customer) {
        List<SalesOrder> orders = this.orders.findByCustomer(customer);
        double ordersPrice = orders.stream().mapToDouble(this::calculateTotalOrderPrice).sum();
        return customer.getCreditLmit() - ordersPrice;
    }

    public double calculateTotalOrderPrice(SalesOrder entity) {
        return entity.getOrderedProducts().stream().mapToDouble(this::calculateTotalProductPrice).sum();
    }

    public double calculateTotalProductPrice(OrderedProduct entity) {
        return entity.getProduct().getPrice() * entity.getQuantity();
    }

    public SalesOrder process(SalesOrder order) {
        SalesOrder existingOrder = orders.findOne(order.getCode());
        if (existingOrder != null) {
            refund(existingOrder);
        }
        return placeOrder(order);
    }

    private void orderProduct(OrderedProduct orderedProduct) {
        validateQuantity(orderedProduct);
        Product product = orderedProduct.getProduct();
        product.setQuantity(product.getQuantity() - orderedProduct.getQuantity());
        products.saveAndFlush(product);
    }

    private SalesOrder placeOrder(SalesOrder order) {
        validateCredit(order);
        order.getOrderedProducts().forEach(this::orderProduct);
        return orders.saveAndFlush(order);
    }

    private void refund(SalesOrder order) {
        order.getOrderedProducts().forEach(this::refundProduct);
        orders.delete(order);
    }

    private void refundProduct(OrderedProduct orderedProduct) {
        Product product = orderedProduct.getProduct();
        product.setQuantity(product.getQuantity() + orderedProduct.getQuantity());
        products.saveAndFlush(product);
    }

    private void validateCredit(SalesOrder order) {
        if (calculateTotalOrderPrice(order) > calculateCredit(order.getCustomer())) {
            throw new IllegalArgumentException("Credit exceeded");
        }
    }
}