package com.springboot.jpa.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UnivResponseDto {

    private String univNm;

    private String domain;

    private String logo;
}
