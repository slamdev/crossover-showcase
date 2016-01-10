package com.dev.rest.model;

public class CustomerDetails extends BaseDto {

    private static final long serialVersionUID = 7920157186783536659L;

    private String address;

    private double creditLmit;

    private double currentCredit;

    private String name;

    private String phone1;

    private String phone2;

    public String getAddress() {
        return address;
    }

    public double getCreditLmit() {
        return creditLmit;
    }

    public double getCurrentCredit() {
        return currentCredit;
    }

    public String getName() {
        return name;
    }

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCreditLmit(double creditLmit) {
        this.creditLmit = creditLmit;
    }

    public void setCurrentCredit(double currentCredit) {
        this.currentCredit = currentCredit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }
}