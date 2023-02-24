package com.springboot.jpa.data.dto;

import com.springboot.jpa.constant.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDto {
    private String email;
    private String age;
    private String kakaoId;
    private String name;
}
