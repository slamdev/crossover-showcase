package com.dev.frontend.panels.edit;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.dev.frontend.services.Services;
import com.dev.rest.model.ProductDetails;

public class EditProduct extends EditContentPanel {

    private static final long serialVersionUID = -8971249970444644844L;

    private JTextField txtCode = new JTextField();

    private JTextField txtDescription = new JTextField();

    private JTextField txtPrice = new JTextField();

    private JTextField txtQuantity = new JTextField();

    public EditProduct() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Code"), gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtCode, gbc);
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        txtCode.setColumns(10);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Description"), gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        add(txtDescription, gbc);
        txtDescription.setColumns(28);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Price"), gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        add(txtPrice, gbc);
        txtPrice.setColumns(10);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(new JLabel("Quantity"), gbc);
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 15);
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        add(txtQuantity, gbc);
        txtQuantity.setColumns(10);
    }

    @Override
    public boolean bindToGUI(Object o) {
        ProductDetails details = (ProductDetails) o;
        txtCode.setText(details.getCode());
        txtDescription.setText(details.getDescirption());
        txtPrice.setText(Double.toString(details.getPrice()));
        txtQuantity.setText(Integer.toString(details.getQuantity()));
        return false;
    }

    @Override
    public void clear() {
        txtCode.setText("");
        txtDescription.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
    }

    @Override
    public String getCurrentCode() {
        return txtCode.getText();
    }

    @Override
    public int getObjectType() {
        return Services.TYPE_PRODUCT;
    }

    @Override
    public Object guiToObject() {
        ProductDetails details = new ProductDetails();
        details.setCode(txtCode.getText());
        details.setDescirption(txtDescription.getText());
        details.setPrice(Double.valueOf(txtPrice.getText()));
        details.setQuantity(Integer.valueOf(txtQuantity.getText()));
        return details;
    }

    @Override
    public void onInit() {
        // nothing to implement
    }
}
