package com.springboot.jpa.data.entity;


import com.springboot.jpa.data.dto.UnivDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "univ")
@Getter
@Setter
@ToString
public class Univ {

    @Id
    @Column(name="univ_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String univNm;  // 대학이름

    @Column(nullable = false)
    private String domain;  // 대학 도메인

    @Column(nullable = false)
    private String logo;    // 대학 로고

}
