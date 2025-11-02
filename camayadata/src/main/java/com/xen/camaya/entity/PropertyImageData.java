package com.xen.camaya.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name ="PropertyImageTable")
public class PropertyImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String url;

    @ManyToOne
    @JoinColumn(name = "property_id")
    @JsonBackReference
    PropertyData property;
}
