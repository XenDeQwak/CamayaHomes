package com.xen.camaya.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="UserTable")
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    String email;
    String password;
    String role;
    Integer adminId;
}
