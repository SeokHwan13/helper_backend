package com.example.helper.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pharmstorage")
public class pharmstorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY) // `medicine_id`는 `Medicine`과 연관 관계
    @JoinColumn(name = "pharm_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_pharmstorage_pharm"))
    private pharm pharm;

    @ManyToOne(fetch = FetchType.EAGER) // `medicine_id`는 `Medicine`과 연관 관계
    @JoinColumn(name = "medicine_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_medicinetype_medicinee"))
    @JsonIgnore
    private medicinee medicinee;

    @Column(name = "remain")
    private Integer remain;
}
