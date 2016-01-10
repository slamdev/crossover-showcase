package com.dev.frontend.panels.list;

import java.util.List;

import com.dev.frontend.services.Services;
import com.dev.rest.model.SalesOrderRow;

public class SalesOrderDataModel extends ListDataModel {

    private static final long serialVersionUID = 7526529951747614655L;

    private static String[] toTable(Object object) {
        SalesOrderRow row = (SalesOrderRow) object;
        return new String[] { row.getCode(), row.getCustomerName(), Double.toString(row.getTotalPrice()) };
    }

    public SalesOrderDataModel() {
        super(new String[] { "Order Number", "Customer", "Total Price" }, 0);
    }

    @Override
    public String[][] convertRecordsListToTableModel(List<Object> list) {
        return list.stream().map(SalesOrderDataModel::toTable).toArray(String[][]::new);
    }

    @Override
    public int getObjectType() {
        return Services.TYPE_SALESORDER;
    }
}
