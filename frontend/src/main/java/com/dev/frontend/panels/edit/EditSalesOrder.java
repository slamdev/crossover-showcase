package com.dev.frontend.panels.edit;

import static java.util.stream.Collectors.toSet;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dev.frontend.panels.ComboBoxItem;
import com.dev.frontend.services.Services;
import com.dev.frontend.services.Utils;
import com.dev.rest.model.SalesOrderDetails;
import com.dev.rest.model.SalesOrderDetails.OrderedProductDetails;

public class EditSalesOrder extends EditContentPanel {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditSalesOrder.class);

    private static final long serialVersionUID = -8971249970444644844L;

    private static OrderedProductDetails toOrderProductDetails(Vector<Object> o) {
        Object[] row = o.toArray();
        OrderedProductDetails details = new OrderedProductDetails();
        details.setProductCode(row[0].toString());
        details.setQuantity(Integer.valueOf(row[1].toString()));
        return details;
    }

    private static Object[] toRow(OrderedProductDetails details) {
        return new Object[] { details.getProductCode(), details.getQuantity(), details.getPrice(), details.getTotal() };
    }

    private JButton btnAddLine = new JButton("Add");

    private DefaultTableModel defaultTableModel = new DefaultTableModel(
            new String[] { "Product", "Qty", "Price", "Total" }, 0) {

        private static final long serialVersionUID = 7058518092777538239L;

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private JTable lines = new JTable(defaultTableModel);

    private JComboBox<ComboBoxItem> txtCustomer = new JComboBox<>();

    private JTextField txtOrderNum = new JTextField();

    private JComboBox<ComboBoxItem> txtProduct = new JComboBox<>();

    private JTextField txtQuantity = new JTextField();

    private JTextField txtTotalPrice = new JTextField();

    public EditSalesOrder() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Order Number"), gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.weightx = 0.5;
        add(txtOrderNum, gbc);
        txtOrderNum.setColumns(10);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 3;
        gbc.gridy = 0;
        add(new JLabel("Customer"), gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        txtCustomer.setSelectedItem("Select a Customer");
        add(txtCustomer, gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Total Price"), gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.weightx = 0.5;
        add(txtTotalPrice, gbc);
        txtTotalPrice.setColumns(10);
        txtTotalPrice.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        add(new JLabel("Details"), gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        JSeparator separator = new JSeparator();
        separator.setPreferredSize(new Dimension(10, 1));
        gbc.fill = GridBagConstraints.BOTH;
        add(separator, gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Product"), gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        txtProduct.setSelectedItem("Select a Product");
        add(txtProduct, gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 3;
        gbc.gridy = 3;
        add(new JLabel("Quantity"), gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        add(txtQuantity, gbc);
        txtQuantity.setColumns(10);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 5, 2, 5);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        add(btnAddLine, gbc);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 7;
        gbc.gridheight = 8;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.ipady = 40;
        gbc.fill = GridBagConstraints.BOTH;
        JScrollPane scrollPane = new JScrollPane(lines, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        lines.setFillsViewportHeight(true);
        add(scrollPane, gbc);
        btnAddLine.addActionListener(e -> addRow());
    }

    public void addRow() {
        ComboBoxItem comboBoxItem = (ComboBoxItem) txtProduct.getSelectedItem();
        if (comboBoxItem == null) {
            JOptionPane.showMessageDialog(this, "You must select a product");
            return;
        }
        String productCode = comboBoxItem.getKey();
        double price = Services.getProductPrice(productCode);
        Integer qty = 0;
        try {
            qty = Integer.parseInt(txtQuantity.getText());
        } catch (Exception e) {
            LOGGER.error("", e);
            JOptionPane.showMessageDialog(this, "Invalid number format in Qty field");
            return;
        }
        double totalPrice = qty * price;
        defaultTableModel.addRow(new String[] { productCode, "" + qty, "" + price, "" + totalPrice });
        double currentValue = Utils.parseDouble(txtTotalPrice.getText());
        currentValue += totalPrice;
        txtTotalPrice.setText("" + currentValue);
    }

    @Override
    public boolean bindToGUI(Object o) {
        SalesOrderDetails details = (SalesOrderDetails) o;
        txtOrderNum.setText(details.getCode());
        txtTotalPrice.setText(Double.toString(details.getTotal()));
        txtCustomer.setSelectedIndex(getIndexByCustomer(details.getCustomerCode()));
        defaultTableModel.setRowCount(0);
        details.getOrderedProductsDetails().stream().map(EditSalesOrder::toRow).forEach(defaultTableModel::addRow);
        return false;
    }

    @Override
    public void clear() {
        txtOrderNum.setText("");
        txtCustomer.removeAllItems();
        txtProduct.removeAllItems();
        txtQuantity.setText("");
        txtTotalPrice.setText("");
        defaultTableModel.setRowCount(0);
    }

    @Override
    public String getCurrentCode() {
        return txtOrderNum.getText();
    }

    @Override
    public int getObjectType() {
        return Services.TYPE_SALESORDER;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object guiToObject() {
        SalesOrderDetails details = new SalesOrderDetails();
        details.setCode(txtOrderNum.getText());
        details.setCustomerCode(txtCustomer.getItemAt(txtCustomer.getSelectedIndex()).getKey());
        details.setOrderedProductsDetails(((Vector<Vector<Object>>) defaultTableModel.getDataVector()).stream()
                .map(EditSalesOrder::toOrderProductDetails).collect(toSet()));
        return details;
    }

    @Override
    public void onInit() {
        List<ComboBoxItem> customers = Services.listCurrentRecordRefernces(Services.TYPE_CUSTOMER);
        for (ComboBoxItem item : customers) {
            txtCustomer.addItem(item);
        }
        List<ComboBoxItem> products = Services.listCurrentRecordRefernces(Services.TYPE_PRODUCT);
        for (ComboBoxItem item : products) {
            txtProduct.addItem(item);
        }
    }

    private int getIndexByCustomer(String customerCode) {
        for (int i = 0; i < txtCustomer.getItemCount(); i++) {
            if (txtCustomer.getItemAt(i).getKey().equals(customerCode)) {
                return i;
            }
        }
        return 0;
    }
}
