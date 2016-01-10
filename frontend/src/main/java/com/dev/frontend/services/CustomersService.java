package com.dev.frontend.services;

import com.dev.rest.model.CustomerDetails;
import com.dev.rest.model.CustomerRow;

public class CustomersService extends BaseService {

    private static final String ENDPOINT = "customers";

    @Override
    protected Class<CustomerDetails> detailsType() {
        return CustomerDetails.class;
    }

    @Override
    protected String endpoint() {
        return ENDPOINT;
    }

    @Override
    protected Class<CustomerRow> rowType() {
        return CustomerRow.class;
    }
}
