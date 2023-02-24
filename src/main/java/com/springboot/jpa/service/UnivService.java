package com.springboot.jpa.service;

import com.springboot.jpa.data.dto.UnivDto;
import com.springboot.jpa.data.dto.UnivResponseDto;
import com.springboot.jpa.data.entity.Univ;

import java.util.Optional;


public interface UnivService {

    //대학이름 검색
    Optional<UnivResponseDto> getUnivByUnivNm(String univNm);

    // 대학 조회
    Optional<UnivResponseDto> getUniv(Long id);

    // 대학 등록
    UnivResponseDto saveUniv(UnivDto univDto);

    // 대학 정보 변경
    UnivResponseDto change(Long id, UnivDto univDto) throws Exception;

    // 대학 정보 삭제
    void delete(Long id) throws Exception;
}
