package com.springboot.jpa.data.dao;

import com.springboot.jpa.data.entity.Univ;

import java.util.Optional;

public interface UnivDAO {

    Univ insertUniv(Univ univ);

    Univ selectUniv(Long id);

   Optional<Univ> selectUnivNm(String univNm);

    Univ updateUniv(Long id, Univ univ) throws Exception;

    void deleteUniv(Long id) throws Exception;
}
