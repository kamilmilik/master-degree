package com.example.masterdegree.controllers;

import com.example.masterdegree.models.dto.CriteriaRequestDto;
import com.example.masterdegree.models.dto.ResultTvPackagesResponseDto;
import com.example.masterdegree.core.filteredresult.FilteredResultService;
import com.example.masterdegree.models.entity.Criteria;
import com.example.masterdegree.models.mappers.CriteriaMapper;
import com.example.masterdegree.models.model.ResultTvPackages;
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
    private final CriteriaMapper criteriaMapper;

    @PostMapping("/result")
    public ResponseEntity<ResultTvPackages> getFilteredResultByCriteria(@RequestBody CriteriaRequestDto criteriaRequestDto) {
        return new ResponseEntity<>(filteredResultService.getFilteredResult(criteriaMapper.convertToEntity(criteriaRequestDto)), HttpStatus.OK);
    }

}
