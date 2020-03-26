package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.dto.ResultTvPackage;
import com.example.masterdegree.core.price.PriceService;

import java.util.List;

public class PriceRangeCriteriaStrategy implements CriteriaStrategy {
    private PriceService priceService;

    public PriceRangeCriteriaStrategy(PriceService priceService) {
        this.priceService = priceService;
    }

    @Override
    public List<ResultTvPackage> getFilteredResult(List<ResultTvPackage> resultTvPackages) {
        return priceService.getResultFilteredByRangePriceInMainTvPackages(resultTvPackages);
    }
}
