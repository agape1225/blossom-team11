package com.springboot.jpa.data.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PostResponseDto {
    private String title;
    private String content;
    private UserDto user;
}
