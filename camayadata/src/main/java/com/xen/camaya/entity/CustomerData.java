package com.xen.camaya.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CustomerTable")

public class CustomerData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String customerName;
    String customerEmail;
    String houseName;
    int customerContactNo;
}
