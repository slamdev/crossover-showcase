package com.dev.service.entity;

import static java.lang.String.format;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
public class Customer extends BaseEntity {

    private static final long serialVersionUID = -2178441304961143310L;

    @Size(min = 1, max = 255)
    private String address;

    private double creditLmit;

    @Size(min = 1, max = 255)
    private String name;

    @Size(min = 1, max = 255)
    private String phone1;

    @Size(min = 1, max = 255)
    private String phone2;

    public String getAddress() {
        return address;
    }

    public double getCreditLmit() {
        return creditLmit;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    @Override
    public String toString() {
        return super.toString() + format(" | name=%s", name);
    }
}
