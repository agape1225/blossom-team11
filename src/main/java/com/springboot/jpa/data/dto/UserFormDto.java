package com.springboot.jpa.data.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserFormDto {

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @NotBlank(message = "나이는 필수 입력 값입니다.")
    private String age;

    @NotBlank(message = "카카오톡 아이디는 필수 입력 값입니다.")
    private String kakaoId;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=8, max=16, message = "비밀번호는 8글자 이상, 16자 이하로 입력해주세요.")
    private String password;
}
