package com.dev.frontend.panels.list;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.dev.frontend.panels.BusinessPresenter;
import com.dev.frontend.services.Services;

public abstract class ListDataModel extends DefaultTableModel implements BusinessPresenter {

    private static final long serialVersionUID = -4086300183434090161L;

    public ListDataModel(String[] columnNames, int rowsCount) {
        super(columnNames, rowsCount);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean bindToGUI(Object o) {
        clear();
        List<Object> list = Services.listCurrentRecords(getObjectType());
        String[][] modelData = convertRecordsListToTableModel(list);
        for (String[] row : modelData) {
            addRow(row);
        }
        return true;
    }

    @Override
    public void clear() {
        setRowCount(0);
    }

    public abstract String[][] convertRecordsListToTableModel(List<Object> list);

    public abstract int getObjectType();

    @Override
    public Object guiToObject() {
        return getDataVector();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public void onInit() {
        bindToGUI(null);
    }
}
