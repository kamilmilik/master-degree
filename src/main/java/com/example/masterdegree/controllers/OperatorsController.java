package com.example.masterdegree.controllers;

import com.example.masterdegree.models.Operator;
import com.example.masterdegree.repositories.OperatorsRepository;
import com.example.masterdegree.services.OperatorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/operators")
public class OperatorsController {

    private OperatorsService operatorsService;

    @Autowired
    public OperatorsController(OperatorsService operatorsService){
        this.operatorsService = operatorsService;
    }

    @GetMapping("/all")
    public List<Operator> getAll(){
        return operatorsService.findAll();
    }

    @PostMapping("/")
    public Operator createOperator(@Valid @RequestBody Operator operator){
        operatorsService.save(operator);
        return operator;
    }
}

