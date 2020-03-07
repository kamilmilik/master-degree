package com.example.masterdegree.controllers;

import com.example.masterdegree.models.Operator;
import com.example.masterdegree.models.Result;
import com.example.masterdegree.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RepositoryRestController
@RequestMapping("/api")
public class ResultController {

    private ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping("/result")
    @ResponseBody
    public Result getResult() {
        return resultService.getResult();
    }

}
