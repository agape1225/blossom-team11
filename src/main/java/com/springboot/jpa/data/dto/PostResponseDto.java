package com.springboot.jpa.data.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PostResponseDto {
    private Long number;
    private String title;
    private String content;
    private UserDto user;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
