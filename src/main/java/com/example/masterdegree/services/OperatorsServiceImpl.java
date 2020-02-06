package com.example.masterdegree.services;

import com.example.masterdegree.models.Operator;
import com.example.masterdegree.repositories.OperatorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorsServiceImpl implements OperatorsService {

    @Autowired
    private OperatorsRepository operatorsRepository;

    @Override
    public List<Operator> findAll() {
        return operatorsRepository.findAll();
    }

    @Override
    public Operator save(Operator var1) {
        return operatorsRepository.save(var1);
    }
}
