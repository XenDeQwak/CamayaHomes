package com.xen.camaya.model;

import lombok.Data;

@Data
public class CustomerModel {
    int id;
    String customerName;
    String customerEmail;
    int customerContactNo;

    @Override
    public String toString() {
        return customerName + customerEmail + customerContactNo;
    }
}
