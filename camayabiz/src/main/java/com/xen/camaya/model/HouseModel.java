package com.xen.camaya.model;

import lombok.Data;

@Data
public class HouseModel {
    int id;
    String houseName;
    String houseLocation;

    @Override
    public String toString() {
        return houseName;
    }
}
