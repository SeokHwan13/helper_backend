package com.example.helper.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "medicine")
public class medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //식별id

    @Column(name = "itemName") //제품명
    private String itemName;

    @Column(name = "entpName") //업체명
    private String entpName;

    @Column(name = "efcyQesitm") //효능
    private String efcyQesitm;

    @Column(name = "useMethod") //복용법
    private String useMethod;

    @Column(name = "atpnQesitm") //주의사항
    private String atpnQesitm;

    @Column(name = "intrcQesitm") //같이 복용시 위험한것
    private String intrcQesitm;

    @Column(name = "seQesitm") //부작용
    private String seQesitm;

    @Column(name = "depositMethod") //보관법
    private String depositMethod;

    @Column(name = "itemImage") //이미지
    private String itemImage;
}

