package com.xen.camaya.entity;

public class Customer {
    int id;
    String customerName;
    String customerAddress;
    int customerContactNo;
    @Override
    public String toString() {
        return customerName + customerAddress + customerContactNo;
    }
}
