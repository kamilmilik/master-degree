package com.example.masterdegree.controllers;

import com.example.masterdegree.models.dto.CriteriaRequestDto;
import com.example.masterdegree.models.dto.ResultTvPackagesResponseDto;
import com.example.masterdegree.core.filteredresult.FilteredResultService;
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
    public ResponseEntity<ResultTvPackagesResponseDto> getFilteredResultByCriteria(@RequestBody CriteriaRequestDto criteriaRequestDto) {
        return new ResponseEntity<>(filteredResultService.getFilteredResult(criteriaRequestDto), HttpStatus.OK);
    }

}
