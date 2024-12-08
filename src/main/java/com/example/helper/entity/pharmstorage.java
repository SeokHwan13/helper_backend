package com.example.helper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class pharmstorage {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "pharm_id")
    private Integer pharm_id;

    @Column(name = "medicine_id")
    private String medicine_id;

    @Column(name = "remain")
    private String remain;
}
