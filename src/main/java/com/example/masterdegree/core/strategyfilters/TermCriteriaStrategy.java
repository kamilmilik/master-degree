package com.example.masterdegree.core.strategyfilters;


import com.example.masterdegree.models.model.Criteria;
import com.example.masterdegree.models.model.filter.ResultTvPackage;

import java.util.List;
import java.util.stream.Collectors;

public class TermCriteriaStrategy implements CriteriaStrategy {

    private final Criteria criteria;

    public TermCriteriaStrategy(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public List<ResultTvPackage> getFilteredResult(List<ResultTvPackage> resultTvPackages) {
        return this.getResultFilteredByTermInMainTvPackages(resultTvPackages);
    }

    private List<ResultTvPackage> getResultFilteredByTermInMainTvPackages(List<ResultTvPackage> resultTvPackages) {
        return resultTvPackages.stream()
                .filter(resultTvPackage -> criteria.getTerm().compareTerm(resultTvPackage.getFilteredTvPackage().getTerm()))
                .collect(Collectors.toList());
    }
}
