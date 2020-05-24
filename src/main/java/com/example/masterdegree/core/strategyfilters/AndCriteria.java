package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.model.filter.ResultTvPackage;
import lombok.Builder;
import lombok.Singular;

import java.util.List;

@Builder
public class AndCriteria implements CriteriaStrategy {

    @Singular
    private final List<CriteriaStrategy> criteriaStrategies;

    @Override
    public List<ResultTvPackage> getFilteredResult(List<ResultTvPackage> resultTvPackages) {
        List<ResultTvPackage> filteredResultTvPackages = resultTvPackages;
        for (CriteriaStrategy criteria : criteriaStrategies) {
            filteredResultTvPackages = criteria.getFilteredResult(filteredResultTvPackages);
        }
        return filteredResultTvPackages;
    }
}
