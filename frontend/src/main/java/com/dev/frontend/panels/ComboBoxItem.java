package com.dev.frontend.panels;

public class ComboBoxItem {

    private String key;

    private String value;

    public ComboBoxItem() {
    }

    public ComboBoxItem(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ComboBoxItem)) {
            return false;
        }
        return getKey().equals(((ComboBoxItem) obj).getKey());
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (key == null ? 0 : key.hashCode());
        return result;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + "(" + key + ")";
    }
}
