package com.xen.camaya.entity;

import javax.persistence.*;

@Entity
@Table(name = "CustomerTable")

public class CustomerData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String customerName;
    String customerEmail;
    int customerContactNo;
}
