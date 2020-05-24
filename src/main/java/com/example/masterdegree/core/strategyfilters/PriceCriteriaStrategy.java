package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.model.Criteria;
import com.example.masterdegree.models.model.filter.ResultTvPackage;

import java.util.List;
import java.util.stream.Collectors;

public class PriceCriteriaStrategy implements CriteriaStrategy {

    private final Criteria criteria;

    public PriceCriteriaStrategy(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public List<ResultTvPackage> getFilteredResult(List<ResultTvPackage> resultTvPackages) {
        return this.getResultFilteredByPriceInMainTvPackages(resultTvPackages);
    }

    private List<ResultTvPackage> getResultFilteredByPriceInMainTvPackages(List<ResultTvPackage> resultTvPackages) {
        return resultTvPackages.stream()
                .filter(resultTvPackage -> criteria.getPrice().isGreaterThan(resultTvPackage.getFilteredTvPackage().getPrice()))
                .collect(Collectors.toList());
    }


}
