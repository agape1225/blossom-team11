package com.springboot.jpa.data.dao.impl;

import com.springboot.jpa.data.dao.UnivDAO;
import com.springboot.jpa.data.entity.Univ;
import com.springboot.jpa.data.repository.UnivRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UnivDAOImpl implements UnivDAO {

    private UnivRepository univRepository;

    @Autowired
    public UnivDAOImpl(UnivRepository univRepository){
        this.univRepository = univRepository;
    }

    @Override
    public Univ insertUniv(Univ univ) {
        Univ savedUniv = univRepository.save(univ);

        return savedUniv;
    }

    @Override
    public Univ selectUniv(Long id) {
        Univ selectedUniv = univRepository.getById(id);

        return selectedUniv;
    }

    @Override
    public Optional<Univ> selectUnivNm(String univNm) {
        Optional<Univ> selectedUniv = univRepository.findByUnivNm(univNm);

        return selectedUniv;
    }

    @Override
    public Univ updateUniv(Long id, Univ univ) throws Exception {
        Optional<Univ> selectedUniv = univRepository.findById(id);

        Univ updatedUniv;
        if(selectedUniv.isPresent()){
            Univ univData = selectedUniv.get();

            univData.setUnivNm(univ.getUnivNm());
            univData.setDomain(univ.getDomain());
            univData.setLogo(univ.getLogo());

            updatedUniv = univRepository.save(univData);
        }else{
            throw new Exception();
        }
        return updatedUniv;
    }

    @Override
    public void deleteUniv(Long id) throws Exception {
        Optional<Univ> selectedUniv = univRepository.findById(id);

        if(selectedUniv.isPresent()){
            Univ univ = selectedUniv.get();

            univRepository.delete(univ);
        }else{
            throw new Exception();
        }
    }
}
