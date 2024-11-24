package com.example.helper.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class pharmacy {

    @Id
    Integer id;

    String tel;

    String post;

    String address;

    String road_address;

    String road_post;

    String name;

    Double lat;

    Double lon;

}
