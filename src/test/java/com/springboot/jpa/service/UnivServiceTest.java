package com.springboot.jpa.service;

import com.springboot.jpa.data.dto.UnivDto;
import com.springboot.jpa.data.dto.UnivResponseDto;
import com.springboot.jpa.data.entity.Univ;
import com.springboot.jpa.data.repository.UnivRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class UnivServiceTest {

    @Autowired
    UnivService univService;

    @Autowired
    UnivRepository univRepository;

}