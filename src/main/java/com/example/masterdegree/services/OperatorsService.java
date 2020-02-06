package com.example.masterdegree.services;

import com.example.masterdegree.models.Operator;


import java.util.List;

public interface OperatorsService {
    List<Operator> findAll();

    Operator save(Operator var1);
}
