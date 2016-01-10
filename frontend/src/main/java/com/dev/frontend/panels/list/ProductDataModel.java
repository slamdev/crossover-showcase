package com.dev.frontend.panels.list;

import java.util.List;

import com.dev.frontend.services.Services;
import com.dev.rest.model.ProductRow;

public class ProductDataModel extends ListDataModel {

    private static final long serialVersionUID = 7526529951747614655L;

    private static String[] toTable(Object object) {
        ProductRow row = (ProductRow) object;
        return new String[] { row.getCode(), row.getDescirption(), Double.toString(row.getPrice()),
                Integer.toString(row.getQuantity()) };
    }

    public ProductDataModel() {
        super(new String[] { "Code", "Description", "Price", "Quantity" }, 0);
    }

    @Override
    public String[][] convertRecordsListToTableModel(List<Object> list) {
        return list.stream().map(ProductDataModel::toTable).toArray(String[][]::new);
    }

    @Override
    public int getObjectType() {
        return Services.TYPE_PRODUCT;
    }
}
