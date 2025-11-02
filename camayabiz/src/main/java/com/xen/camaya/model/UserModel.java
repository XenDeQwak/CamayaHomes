package com.xen.camaya.model;

import java.util.List;

import lombok.Data;

@Data
public class UserModel {
    int id;
    String name;
    String email;
    String password;
    String role;
    Integer adminId;
    List<Integer> linkedProperties;
}
