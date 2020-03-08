package com.example.masterdegree.services.strategyfilters;

import com.example.masterdegree.models.ResultTvPackage;
import com.example.masterdegree.services.OperatorsService;

import java.util.List;

public class OperatorCriteria implements Criteria {

    private OperatorsService operatorsService;

    public OperatorCriteria(OperatorsService operatorsService) {
        this.operatorsService = operatorsService;
    }

    @Override
    public List<ResultTvPackage> getFilteredResult(List<ResultTvPackage> resultTvPackages) {
        return operatorsService.getResultByFetchedOperators(resultTvPackages);
    }
}
