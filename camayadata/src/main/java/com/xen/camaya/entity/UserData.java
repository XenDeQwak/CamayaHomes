package com.xen.camaya.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "UserTable")
@ToString(exclude = "linkedProperties")
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String email;
    String password;
    String role;
    Integer adminId;

    @ManyToMany(mappedBy = "linkedUsers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    List<PropertyData> linkedProperties = new ArrayList<>();
}
