package com.example.helper.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pharm")
public class pharm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INC
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "lat")
    Double lat;

    @Column(name = "lon")
    Double lon;

    @Column(name = "phone")
    String phone;
}
