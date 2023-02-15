package com.springboot.jpa.controller;

import com.springboot.jpa.data.dto.*;
import com.springboot.jpa.service.UnivService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/univ")
public class UnivController {

    private final UnivService univService;

    @Autowired
    public UnivController(UnivService univService) {
        this.univService = univService;
    }

    @GetMapping()
    public ResponseEntity<Optional<UnivResponseDto>> getUniv(Long id) {
        Optional<UnivResponseDto> univResponseDto = univService.getUniv(id);

        return (ResponseEntity.status(HttpStatus.OK).body(univResponseDto));
    }

    @GetMapping("/{univNm}")
    public ResponseEntity<Optional<UnivResponseDto>> getUnivByUnivNm(@PathVariable("univNm") String univNm){
        return ResponseEntity.status(HttpStatus.OK).body(univService.getUnivByUnivNm(univNm));
    }

    @PostMapping()
    public ResponseEntity<UnivResponseDto> createUniv(@RequestBody UnivDto univDto) {
        return ResponseEntity.status(HttpStatus.OK).body(univService.saveUniv(univDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnivResponseDto> changeUniv(
            @PathVariable("id") long id,
            @RequestBody UnivDto univDto) throws Exception {
        return  ResponseEntity.status(HttpStatus.OK).body(univService.change(id, univDto));
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteUniv(Long number) throws Exception {
        univService.delete(number);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }

}
