package com.springboot.jpa.service.impl;

import com.springboot.jpa.data.dao.UnivDAO;
import com.springboot.jpa.data.dto.UnivDto;
import com.springboot.jpa.data.dto.UnivResponseDto;
import com.springboot.jpa.data.entity.Univ;
import com.springboot.jpa.data.repository.UnivRepository;
import com.springboot.jpa.service.UnivService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnivServiceImpl implements UnivService {

    private final UnivDAO univDAO;
    private final UnivRepository univRepository;

    @Autowired
    public UnivServiceImpl(UnivDAO univDAO, UnivRepository univRepository) {
        this.univDAO = univDAO;
        this.univRepository = univRepository;
    }

    @Override
    public Optional<UnivResponseDto> getUniv(Long id) {
        Univ univ = univDAO.selectUniv(id);

        UnivResponseDto univResponseDto = new UnivResponseDto();
        univResponseDto.setUnivNm(univ.getUnivNm());
        univResponseDto.setDomain(univ.getDomain());
        univResponseDto.setLogo(univ.getLogo());

        return Optional.of(univResponseDto);
    }

    @Override
    public Optional<UnivResponseDto> getUnivByUnivNm(String univNm) {
        Optional<Univ> univ = univDAO.selectUnivNm(univNm);

        UnivResponseDto univResponseDto = new UnivResponseDto();
        univResponseDto.setUnivNm(univ.get().getUnivNm());
        univResponseDto.setDomain(univ.get().getDomain());
        univResponseDto.setLogo(univ.get().getLogo());

        return Optional.of(univResponseDto);
    }

    @Override
    public UnivResponseDto saveUniv(UnivDto univDto) {
        Univ univ = new Univ();
        univ.setUnivNm(univDto.getUnivNm());
        univ.setDomain(univDto.getDomain());
        univ.setLogo(univDto.getLogo());

        Univ saveUniv = univDAO.insertUniv(univ);

        UnivResponseDto univResponseDto = new UnivResponseDto();
        univResponseDto.setUnivNm(saveUniv.getUnivNm());
        univResponseDto.setDomain(saveUniv.getDomain());
        univResponseDto.setLogo(saveUniv.getLogo());

        return univResponseDto;
    }

    @Override
    public UnivResponseDto change(Long id, UnivDto univDto) throws Exception {
        Univ univ = new Univ();
        univ.setUnivNm(univDto.getUnivNm());
        univ.setDomain(univDto.getDomain());
        univ.setLogo(univDto.getLogo());

        Univ changedUniv = univDAO.updateUniv(id, univ);

        UnivResponseDto univResponseDto = new UnivResponseDto();
        univResponseDto.setUnivNm(changedUniv.getUnivNm());
        univResponseDto.setDomain(changedUniv.getDomain());
        univResponseDto.setLogo(changedUniv.getLogo());

        return univResponseDto;
    }

    @Override
    public void delete(Long id) throws Exception {
        univDAO.deleteUniv(id);
    }
}
