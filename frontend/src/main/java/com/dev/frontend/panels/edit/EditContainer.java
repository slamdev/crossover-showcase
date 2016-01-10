package com.dev.frontend.panels.edit;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dev.frontend.panels.BusinessPresenter;
import com.dev.frontend.panels.HasBusinessPresenter;
import com.dev.frontend.panels.MenuPanel;
import com.dev.frontend.panels.PanelSwitcher;
import com.dev.frontend.services.Services;

public class EditContainer extends JPanel implements ActionListener, HasBusinessPresenter {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditContainer.class);

    private static final long serialVersionUID = -7388350255798160262L;

    private PanelSwitcher cardSwitcher;

    private String CLOSE_ACTION = "close";

    private String DELETE_ACTION = "delete";

    private EditContentPanel editPanel;

    private int objectType;

    private String SAVE_ACTION = "save";

    private JToolBar toolbar;

    public EditContainer(EditContentPanel editPanel, PanelSwitcher cardSwitcher) {
        setLayout(new BorderLayout());
        this.cardSwitcher = cardSwitcher;
        toolbar = new JToolBar();
        add(toolbar, BorderLayout.PAGE_START);
        addToolBarButton(SAVE_ACTION, "Save");
        addToolBarButton(DELETE_ACTION, "Delete");
        addToolBarButton(CLOSE_ACTION, "Close");
        this.editPanel = editPanel;
        objectType = editPanel.getObjectType();
        add(editPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals(SAVE_ACTION)) {
            Object currentObject = editPanel.guiToObject();
            try {
                currentObject = Services.save(currentObject, objectType);
                editPanel.bindToGUI(currentObject);
            } catch (Exception ee) {
                LOGGER.error("", ee);
                JOptionPane.showMessageDialog(this, "Record Not Saved");
            }
        } else if (actionCommand.equals(DELETE_ACTION)) {
            boolean retValue = Services.deleteRecordByCode(editPanel.getCurrentCode(), objectType);
            if (retValue) {
                goToHome();
            } else {
                JOptionPane.showMessageDialog(this, "Couldn't Delete Record");
            }
        } else if (actionCommand.equals(CLOSE_ACTION)) {
            goToHome();
        }
    }

    @Override
    public BusinessPresenter getBusinessPresenter() {
        return editPanel;
    }

    void addToolBarButton(String name, String text) {
        String imgLocation = name + ".png";
        URL imageURL = EditContainer.class.getResource(imgLocation);
        JButton button = new JButton();
        button.setActionCommand(name);
        button.setToolTipText(text);
        button.addActionListener(this);
        if (imageURL != null) {
            ImageIcon defaultIcon = new ImageIcon(imageURL, text);
            button.setIcon(defaultIcon);
        } else {
            button.setText(text);
        }
        toolbar.add(button);
    }

    private void goToHome() {
        cardSwitcher.switchTo(MenuPanel.class.getName());
    }
}
