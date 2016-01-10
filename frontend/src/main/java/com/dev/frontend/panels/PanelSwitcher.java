package com.dev.frontend.panels;

public interface PanelSwitcher {

    HasBusinessPresenter getPanelOfClass(String name);

    void switchTo(String name);
}
