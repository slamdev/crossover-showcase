package com.dev.frontend.services;

import java.util.List;

import com.dev.frontend.panels.ComboBoxItem;
import com.dev.rest.model.BaseDto;

public class Services {

    public static final int TYPE_CUSTOMER = 2;

    public static final int TYPE_PRODUCT = 1;

    public static final int TYPE_SALESORDER = 3;

    /**
     * This method is called when you click delete button on an edit view the code parameter is the code of (Customer -
     * PRoduct) or order number of Sales Order and the type is identifier of the object type and may be TYPE_PRODUCT,
     * TYPE_CUSTOMER or TYPE_SALESORDER
     */
    public static boolean deleteRecordByCode(String code, int objectType) {
        return service(objectType).delete(code);
    }

    /**
     * This method is used to get unit price of product with the code passed as a parameter
     */
    public static double getProductPrice(String productCode) {
        return ((ProductsService) service(TYPE_PRODUCT)).getProductPrice(productCode);
    }

    /**
     * This method is called when a Combo Box need to be initialized and should return list of ComboBoxItem which
     * contains code and description/name for all records of specified type
     */
    public static List<ComboBoxItem> listCurrentRecordRefernces(int objectType) {
        return service(objectType).getAllReferences();
    }

    /**
     * This method is called when you open any list screen and should return all records of the specified type
     */
    @SuppressWarnings("rawtypes")
    public static List listCurrentRecords(int objectType) {
        return service(objectType).getAll();
    }

    /**
     * This method is called when you select record in list view of any entity and also called after you save a record
     * to re-bind the record again the code parameter is the first column of the row you have selected and the type is
     * identifier of the object type and may be TYPE_PRODUCT, TYPE_CUSTOMER or TYPE_SALESORDER
     */
    public static Object readRecordByCode(String code, int objectType) {
        return service(objectType).get(code);
    }

    /**
     * This method is called eventually after you click save on any edit screen object parameter is the return object
     * from calling method guiToObject on edit screen and the type is identifier of the object type and may be
     * TYPE_PRODUCT, TYPE_CUSTOMER or TYPE_SALESORDER
     */
    public static Object save(Object object, int objectType) {
        return service(objectType).save((BaseDto) object);
    }

    private static BaseService service(int objectType) {
        switch (objectType) {
        case TYPE_CUSTOMER:
            return new CustomersService();
        case TYPE_PRODUCT:
            return new ProductsService();
        case TYPE_SALESORDER:
            return new SalesOrdersService();
        default:
            throw new IllegalArgumentException(Integer.toString(objectType));
        }
    }
}
