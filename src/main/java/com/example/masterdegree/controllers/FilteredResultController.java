package com.example.masterdegree.controllers;

import com.example.masterdegree.models.dto.CriteriaRequestDto;
import com.example.masterdegree.core.filteredresult.FilteredResultService;
import com.example.masterdegree.models.model.filter.ResultTvPackages;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FilteredResultController {

    private final FilteredResultService filteredResultService;

    @PostMapping("/result")
    public ResponseEntity<ResultTvPackages> getFilteredResultByCriteria(@RequestBody CriteriaRequestDto criteriaRequestDto) {
        return new ResponseEntity<>(filteredResultService.getFilteredResult(criteriaRequestDto), HttpStatus.OK);
    }

}
