package com.dev.service.boundary;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.rest.model.ProductDetails;
import com.dev.rest.model.ProductRow;
import com.dev.rest.model.Reference;
import com.dev.service.entity.Product;

@RestController
@RequestMapping("products")
public class ProductsEndpoint extends BaseEndpoint<Product, ProductDetails, ProductRow> {

    @Autowired
    private ProductRepository products;

    @RequestMapping(value = "{code}/price", method = GET)
    public double getPrice(@PathVariable String code) {
        return products.getPrice(code);
    }

    @Override
    protected ProductDetails convertToDetails(Product entity) {
        return convertToRow(entity);
    }

    @Override
    protected Product convertToEntity(ProductDetails details) {
        Product entity = new Product();
        entity.setCode(details.getCode());
        entity.setDescirption(details.getDescirption());
        entity.setPrice(details.getPrice());
        entity.setQuantity(details.getQuantity());
        return entity;
    }

    @Override
    protected ProductRow convertToRow(Product entity) {
        ProductRow row = new ProductRow();
        row.setCode(entity.getCode());
        row.setDescirption(entity.getDescirption());
        row.setPrice(entity.getPrice());
        row.setQuantity(entity.getQuantity());
        return row;
    }

    @Override
    protected JpaRepository<Product, String> getRepository() {
        return products;
    }

    @Override
    protected Reference reference(Product entity) {
        return new Reference(entity.getCode(), entity.getDescirption());
    }
}