package com.xen.camaya.model;

import lombok.Data;

@Data
public class PropertyModel {
    int id;
    String name;
    String location;
    String description;
    double price;
    boolean isBought;
    Integer linkedUser;
}
