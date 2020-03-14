package com.example.masterdegree.controllers;

import com.example.masterdegree.models.dto.ResultTvPackages;
import com.example.masterdegree.services.result.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RepositoryRestController
@RequestMapping("/api")
public class ResultController {

    private ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping("/result")
//    @ResponseBody
    public ResponseEntity<ResultTvPackages> getResult() {
        return new ResponseEntity<>(resultService.getResult(), HttpStatus.OK);
    }

}
