package com.dev.rest.model;

public class CustomerRow extends BaseDto {

    private static final long serialVersionUID = 7920157186783536659L;

    private double currentCredit;

    private String name;

    private String phone;

    public double getCurrentCredit() {
        return currentCredit;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setCurrentCredit(double currentCredit) {
        this.currentCredit = currentCredit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}