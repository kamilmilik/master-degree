package com.example.masterdegree.controllers;

import com.example.masterdegree.models.dto.ResultTvPackages;
import com.example.masterdegree.services.filteredresult.FilteredResultService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class FilteredResultController {

    private FilteredResultService filteredResultService;

    @Autowired
    public FilteredResultController(FilteredResultService filteredResultService) {
        this.filteredResultService = filteredResultService;
    }

    @GetMapping("/result")
//    @ResponseBody
    public ResponseEntity<ResultTvPackages> getResult() {
        return new ResponseEntity<>(filteredResultService.getFilteredResult(), HttpStatus.OK);
    }

}
