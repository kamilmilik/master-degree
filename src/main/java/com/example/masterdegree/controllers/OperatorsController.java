package com.example.masterdegree.controllers;

import com.example.masterdegree.models.entity.Operator;
import com.example.masterdegree.services.operator.OperatorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@RepositoryRestController
public class OperatorsController {

    private OperatorsService operatorsService;

    @Autowired
    public OperatorsController(OperatorsService operatorsService) {
        this.operatorsService = operatorsService;
    }

    @PostMapping("/operators/selected") // http://localhost:8095/api/operators/selected
    @ResponseBody
    public void fetchSelectedOperator(@RequestBody Operator operator){
        System.out.println("Jestem tutaj");
        operatorsService.addFetchedOperatorResourceToFetchedList(operator);
        System.out.println("Operatory: ");
        operatorsService.getFetchedSelectedOperators().stream().forEach(System.out::println);
    }

    @PostMapping("/operators/not-selected")
    @ResponseBody
    public void fetchNotSelectedOperator(@RequestBody Operator operator){
        System.out.println("Jestem tutaj not selected");
        operatorsService.removeFetchedOperatorResourceFromList(operator);
        System.out.println("Operatory: ");
        operatorsService.getFetchedSelectedOperators().stream().forEach(System.out::println);
    }

}
