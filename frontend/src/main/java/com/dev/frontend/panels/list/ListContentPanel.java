package com.dev.frontend.panels.list;

import java.awt.BorderLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import com.dev.frontend.panels.BusinessPresenter;
import com.dev.frontend.panels.HasBusinessPresenter;
import com.dev.frontend.panels.MenuPanel;
import com.dev.frontend.panels.PanelSwitcher;
import com.dev.frontend.panels.edit.EditContainer;
import com.dev.frontend.panels.edit.EditCustomer;
import com.dev.frontend.panels.edit.EditProduct;
import com.dev.frontend.panels.edit.EditSalesOrder;
import com.dev.frontend.services.Services;

public class ListContentPanel extends JPanel implements HasBusinessPresenter {

    private static final long serialVersionUID = 8776244976111640079L;

    private static String getEditViewName(int type) {
        if (type == Services.TYPE_CUSTOMER) {
            return EditCustomer.class.getName();
        }
        if (type == Services.TYPE_PRODUCT) {
            return EditProduct.class.getName();
        }
        return EditSalesOrder.class.getName();
    }

    ListDataModel dataModel;

    PanelSwitcher panelSwitcher;

    JTable table;

    private JToolBar toolbar;

    public ListContentPanel(PanelSwitcher cardSwitcher, ListDataModel dataModel) {
        panelSwitcher = cardSwitcher;
        this.dataModel = dataModel;
        toolbar = new JToolBar();
        setLayout(new BorderLayout());
        String imgLocation = "close.png";
        URL imageURL = EditContainer.class.getResource(imgLocation);
        JButton button = new JButton();
        button.setActionCommand("close");
        button.setToolTipText("Close");
        button.addActionListener(e -> goToHome());
        if (imageURL != null) {
            ImageIcon defaultIcon = new ImageIcon(imageURL, "Close");
            button.setIcon(defaultIcon);
        } else {
            button.setText("Close");
        }
        toolbar.add(button);
        add(toolbar, BorderLayout.PAGE_START);
        table = new JTable(dataModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                goToRecord(table.getValueAt(table.getSelectedRow(), 0).toString());
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        table.setFillsViewportHeight(true);
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public BusinessPresenter getBusinessPresenter() {
        return dataModel;
    }

    private void goToHome() {
        panelSwitcher.switchTo(MenuPanel.class.getName());
    }

    private void goToRecord(String code) {
        String editViewName = getEditViewName(dataModel.getObjectType());
        HasBusinessPresenter panelOfClass = panelSwitcher.getPanelOfClass(editViewName);
        Object retValue = Services.readRecordByCode(code, dataModel.getObjectType());
        panelSwitcher.switchTo(editViewName);
        panelOfClass.getBusinessPresenter().bindToGUI(retValue);
    }
}
