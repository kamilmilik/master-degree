package com.example.masterdegree.controllers;

import com.example.masterdegree.models.Operator;
import com.example.masterdegree.repositories.OperatorsRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/operators")
public class OperatorsController {

    private OperatorsRepository operatorsRepository;

    public OperatorsController(OperatorsRepository operatorsRepository) {
        this.operatorsRepository = operatorsRepository;
    }

    @GetMapping("/all")
    public List<Operator> getAll(){
        return operatorsRepository.findAll();
    }

    @PostMapping("/")
    public Operator createOperator(@Valid @RequestBody Operator operator){
        operatorsRepository.save(operator);
        return operator;
    }
}

