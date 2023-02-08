package com.springboot.jpa.data.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "user")
public class User extends BaseEntity
{

    @Id
    private String email;

    @Column
    private String name;

    @Column
    private String age;

    @Column
    private String kakaoId;

}
