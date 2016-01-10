package com.dev.frontend.panels.list;

import java.util.List;

import com.dev.frontend.services.Services;
import com.dev.rest.model.CustomerRow;

public class CustomerDataModel extends ListDataModel {

    private static final long serialVersionUID = 7526529951747613655L;

    private static String[] toTable(Object object) {
        CustomerRow row = (CustomerRow) object;
        return new String[] { row.getCode(), row.getName(), row.getPhone(), Double.toString(row.getCurrentCredit()) };
    }

    public CustomerDataModel() {
        super(new String[] { "Code", "Name", "Phone", "Current Credit" }, 0);
    }

    @Override
    public String[][] convertRecordsListToTableModel(List<Object> list) {
        return list.stream().map(CustomerDataModel::toTable).toArray(String[][]::new);
    }

    @Override
    public int getObjectType() {
        return Services.TYPE_CUSTOMER;
    }
}
