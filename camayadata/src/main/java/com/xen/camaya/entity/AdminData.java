package com.xen.camaya.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "AdminTable")

public class AdminData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    String role;
}
