package com.dev.frontend.services;

import com.dev.rest.model.SalesOrderDetails;
import com.dev.rest.model.SalesOrderRow;

public class SalesOrdersService extends BaseService {

    private static final String ENDPOINT = "orders";

    @Override
    protected Class<SalesOrderDetails> detailsType() {
        return SalesOrderDetails.class;
    }

    @Override
    protected String endpoint() {
        return ENDPOINT;
    }

    @Override
    protected Class<SalesOrderRow> rowType() {
        return SalesOrderRow.class;
    }
}
