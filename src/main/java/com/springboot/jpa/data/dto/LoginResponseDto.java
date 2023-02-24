package com.springboot.jpa.data.dto;

import com.springboot.jpa.constant.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LoginResponseDto {
    private String email;
    private String password;
    private Role role;
}
