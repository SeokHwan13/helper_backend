package com.example.helper.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class pharmstorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "pharm_id")
    private Integer pharm_id;

    @Column(name = "medicine_id")
    private Integer medicine_id;

    @Column(name = "remain")
    private Integer remain;
}
