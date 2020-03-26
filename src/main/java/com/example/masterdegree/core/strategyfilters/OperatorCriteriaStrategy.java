package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.dto.ResultTvPackage;
import com.example.masterdegree.core.operator.OperatorsService;

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
