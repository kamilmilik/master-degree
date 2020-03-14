package com.example.masterdegree.services.strategyfilters;

import com.example.masterdegree.models.dto.ResultTvPackage;
import com.example.masterdegree.services.operator.OperatorsService;

import java.util.List;

public class OperatorCriteriaStrategy implements CriteriaStrategy {

    private OperatorsService operatorsService;

    public OperatorCriteriaStrategy(OperatorsService operatorsService) {
        this.operatorsService = operatorsService;
    }

    @Override
    public List<ResultTvPackage> getFilteredResult(List<ResultTvPackage> resultTvPackages) {
        return operatorsService.getResultBySelectedOperators(resultTvPackages);
    }
}
