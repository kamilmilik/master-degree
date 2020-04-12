package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.model.Criteria;
import com.example.masterdegree.models.model.filter.ResultTvPackage;

import java.util.List;
import java.util.stream.Collectors;

public class PriceCriteriaStrategy implements CriteriaStrategy {

    private Criteria criteria;

    public PriceCriteriaStrategy(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public List<ResultTvPackage> getFilteredResult(List<ResultTvPackage> resultTvPackages) {
        return this.getResultFilteredByRangePriceInMainTvPackages(resultTvPackages);
    }

    public List<ResultTvPackage> getResultFilteredByRangePriceInMainTvPackages(List<ResultTvPackage> resultTvPackages) {
        return resultTvPackages.stream()
                .filter(resultTvPackage -> criteria.getPrice().isGreaterThan(resultTvPackage.getFilteredTvPackage().getPrice()))
                .collect(Collectors.toList());
    }


}
