package com.example.masterdegree.services.strategyfilters;

import com.example.masterdegree.models.dto.ResultTvPackage;

import java.util.List;

public class AndCriteriaStrategy implements CriteriaStrategy {

    private CriteriaStrategy[] criterias;

    public AndCriteriaStrategy(CriteriaStrategy... criterias) {
        this.criterias = criterias;
    }

    @Override
    public List<ResultTvPackage> getFilteredResult(List<ResultTvPackage> resultTvPackages) {
        List<ResultTvPackage> filteredResultTvPackages = resultTvPackages;
        for (CriteriaStrategy criteria : criterias) {
            filteredResultTvPackages = criteria.getFilteredResult(filteredResultTvPackages);
        }
        return filteredResultTvPackages;
    }
}
