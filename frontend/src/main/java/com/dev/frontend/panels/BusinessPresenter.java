package com.dev.frontend.panels;

public interface BusinessPresenter {

    boolean bindToGUI(Object o);

    void clear();

    Object guiToObject();

    void onInit();
}
