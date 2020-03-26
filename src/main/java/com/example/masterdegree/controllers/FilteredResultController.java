package com.example.masterdegree.controllers;

import com.example.masterdegree.models.dto.ResultTvPackages;
import com.example.masterdegree.core.filteredresult.FilteredResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FilteredResultController {

    private final FilteredResultService filteredResultService;

    @GetMapping("/result")
//    @ResponseBody
    public ResponseEntity<ResultTvPackages> getResult() {
        return new ResponseEntity<>(filteredResultService.getFilteredResult(), HttpStatus.OK);
    }

}
