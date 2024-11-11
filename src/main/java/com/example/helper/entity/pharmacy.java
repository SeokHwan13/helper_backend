package com.example.helper.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class pharmacy {

    @Id
    Integer id;

    String name;

    String post;

    String road_post;

    String address;

    String road_address;

    String lat;

    String lon;

}
