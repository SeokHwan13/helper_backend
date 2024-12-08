package com.example.helper.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "medicinetype")
public class medicinetype {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 매핑
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY) // `medicine_id`는 `Medicine`과 연관 관계
    @JoinColumn(name = "medicine_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_medicinetype_medicinee"))
    private medicine medicine;

    @Column(name = "type")
    private Integer type;
}
