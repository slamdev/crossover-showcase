package com.dev.frontend.services;

import org.springframework.web.client.RestTemplate;

import com.dev.rest.model.ProductDetails;
import com.dev.rest.model.ProductRow;

public class ProductsService extends BaseService {

    private static final String ENDPOINT = "products";

    public double getProductPrice(String productCode) {
        return new RestTemplate().getForObject(uri("{code}/price", productCode), Double.class);
    }

    @Override
    protected Class<ProductDetails> detailsType() {
        return ProductDetails.class;
    }

    @Override
    protected String endpoint() {
        return ENDPOINT;
    }

    @Override
    protected Class<ProductRow> rowType() {
        return ProductRow.class;
    }
}
