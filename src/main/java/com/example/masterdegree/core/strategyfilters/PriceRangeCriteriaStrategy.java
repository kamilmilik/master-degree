package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.entity.Criteria;
import com.example.masterdegree.models.model.ResultTvPackage;

import java.util.List;
import java.util.stream.Collectors;

public class PriceRangeCriteriaStrategy implements CriteriaStrategy {

    private Criteria criteria;

    public PriceRangeCriteriaStrategy(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public List<ResultTvPackage> getFilteredResult(List<ResultTvPackage> resultTvPackages) {
        return this.getResultFilteredByRangePriceInMainTvPackages(resultTvPackages);
    }

    public List<ResultTvPackage> getResultFilteredByRangePriceInMainTvPackages(List<ResultTvPackage> resultTvPackages) {
        return resultTvPackages.stream().
                filter(resultTvPackage -> criteria.getPrice().isBetween(resultTvPackage.getFilteredTvPackage().getPrice()))
                .collect(Collectors.toList());
    }


}
