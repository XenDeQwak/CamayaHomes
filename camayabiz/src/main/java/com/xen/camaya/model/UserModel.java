package com.xen.camaya.model;

import lombok.Data;

@Data
public class UserModel {
    int id;
    String name;
    String email;
    String password;
    String role;
}
