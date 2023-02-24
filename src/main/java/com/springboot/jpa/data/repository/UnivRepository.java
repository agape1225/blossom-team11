package com.springboot.jpa.data.repository;

import com.springboot.jpa.data.dto.UnivResponseDto;
import com.springboot.jpa.data.entity.Univ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnivRepository extends JpaRepository<Univ, Long> {

    Optional<Univ> findByUnivNm(String univNm);

    Univ findByDomain(String domain);
}
