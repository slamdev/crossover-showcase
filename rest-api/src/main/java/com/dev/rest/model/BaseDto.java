package com.dev.rest.model;

import java.io.Serializable;

public abstract class BaseDto implements Serializable {

    private static final long serialVersionUID = -920730305380007067L;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}