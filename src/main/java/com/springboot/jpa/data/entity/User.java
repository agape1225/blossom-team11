package com.springboot.jpa.data.entity;

import com.springboot.jpa.constant.Role;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
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

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Post> postList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    private String univ;
}
