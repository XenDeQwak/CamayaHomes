package com.xen.camaya.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "PropertyTable")
@ToString(exclude = "linkedUsers")
public class PropertyData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String location;
    String description;
    double price;
    boolean isBought;

    @ManyToMany
    @JoinTable(
        name = "user_property_link",
        joinColumns = @JoinColumn(name = "property_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    List<UserData> linkedUsers = new ArrayList<>();

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    List<PropertyImageData> images = new ArrayList<>();

}