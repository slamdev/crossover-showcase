package com.dev.frontend.panels;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.dev.frontend.panels.edit.EditContainer;
import com.dev.frontend.panels.edit.EditCustomer;
import com.dev.frontend.panels.edit.EditProduct;
import com.dev.frontend.panels.edit.EditSalesOrder;
import com.dev.frontend.panels.list.CustomerDataModel;
import com.dev.frontend.panels.list.ListContentPanel;
import com.dev.frontend.panels.list.ProductDataModel;
import com.dev.frontend.panels.list.SalesOrderDataModel;

public class Main implements PanelSwitcher, CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        new SpringApplicationBuilder(Main.class).headless(false).run(args);
    }

    private HashMap<String, HasBusinessPresenter> containersMap = new HashMap<>();

    private JFrame frame;

    private JPanel panel;

    @Override
    public HasBusinessPresenter getPanelOfClass(String name) {
        return containersMap.get(name);
    }

    @Override
    public void run(String... args) {
        initialize();
        EventQueue.invokeLater(() -> {
            try {
                MenuPanel menuPanel = new MenuPanel(this);
                panel.add(menuPanel, MenuPanel.class.getName());
                EditContainer productContainer = new EditContainer(new EditProduct(), this);
                EditContainer customerContainer = new EditContainer(new EditCustomer(), this);
                EditContainer salesOrderContainer = new EditContainer(new EditSalesOrder(), this);
                addPanel(productContainer, EditProduct.class.getName());
                addPanel(customerContainer, EditCustomer.class.getName());
                addPanel(salesOrderContainer, EditSalesOrder.class.getName());
                addPanel(new ListContentPanel(this, new CustomerDataModel()), CustomerDataModel.class.getName());
                addPanel(new ListContentPanel(this, new ProductDataModel()), ProductDataModel.class.getName());
                addPanel(new ListContentPanel(this, new SalesOrderDataModel()), SalesOrderDataModel.class.getName());
                frame.setVisible(true);
            } catch (Exception e) {
                LOGGER.error("", e);
            }
        });
    }

    @Override
    public void switchTo(String name) {
        CardLayout layout = (CardLayout) panel.getLayout();
        HasBusinessPresenter container = getPanelOfClass(name);
        if (container != null) {
            container.getBusinessPresenter().clear();
            container.getBusinessPresenter().onInit();
        }
        layout.show(panel, name);
    }

    void addPanel(HasBusinessPresenter container, String name) {
        containersMap.put(name, container);
        panel.add((Component) container, name);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel(new CardLayout());
        frame.add(panel);
    }
}
