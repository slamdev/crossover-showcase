package com.dev.service.boundary;

import static java.util.stream.Collectors.toSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.rest.model.Reference;
import com.dev.rest.model.SalesOrderDetails;
import com.dev.rest.model.SalesOrderDetails.OrderedProductDetails;
import com.dev.rest.model.SalesOrderRow;
import com.dev.service.entity.OrderedProduct;
import com.dev.service.entity.SalesOrder;

@RestController
@RequestMapping("orders")
public class SalesOrdersEndpoint extends BaseEndpoint<SalesOrder, SalesOrderDetails, SalesOrderRow> {

    @Autowired
    private CustomerRepository customers;

    @Autowired
    private SalesOrderRepository orders;

    @Autowired
    private ProductRepository products;

    @Autowired
    private OrderService service;

    @Override
    public SalesOrderDetails save(@RequestBody SalesOrderDetails dto) {
        SalesOrder entity = convertToEntity(dto);
        entity = service.process(entity);
        return convertToDetails(entity);
    }

    @Override
    protected SalesOrderDetails convertToDetails(SalesOrder entity) {
        SalesOrderDetails details = new SalesOrderDetails();
        details.setCode(entity.getCode());
        details.setCustomerCode(entity.getCustomer().getCode());
        details.setTotal(service.calculateTotalOrderPrice(entity));
        details.setOrderedProductsDetails(
                entity.getOrderedProducts().stream().map(this::convertToOrderedProductDetails).collect(toSet()));
        return details;
    }

    @Override
    protected SalesOrder convertToEntity(SalesOrderDetails details) {
        SalesOrder entity = new SalesOrder();
        entity.setCode(details.getCode());
        entity.setCustomer(customers.getOne(details.getCustomerCode()));
        entity.setOrderedProducts(
                details.getOrderedProductsDetails().stream().map(this::convertToOrderedProduct).collect(toSet()));
        return entity;
    }

    @Override
    protected SalesOrderRow convertToRow(SalesOrder entity) {
        SalesOrderRow row = new SalesOrderRow();
        row.setCode(entity.getCode());
        row.setCustomerName(entity.getCustomer().getName());
        row.setTotalPrice(service.calculateTotalOrderPrice(entity));
        return row;
    }

    @Override
    protected JpaRepository<SalesOrder, String> getRepository() {
        return orders;
    }

    @Override
    protected Reference reference(SalesOrder entity) {
        throw new UnsupportedOperationException();
    }

    private OrderedProduct convertToOrderedProduct(OrderedProductDetails details) {
        OrderedProduct entity = new OrderedProduct();
        entity.setProduct(products.getOne(details.getProductCode()));
        entity.setQuantity(details.getQuantity());
        return entity;
    }

    private OrderedProductDetails convertToOrderedProductDetails(OrderedProduct entity) {
        OrderedProductDetails details = new OrderedProductDetails();
        details.setProductCode(entity.getProduct().getCode());
        details.setQuantity(entity.getQuantity());
        details.setPrice(entity.getProduct().getPrice());
        details.setTotal(service.calculateTotalProductPrice(entity));
        return details;
    }
}