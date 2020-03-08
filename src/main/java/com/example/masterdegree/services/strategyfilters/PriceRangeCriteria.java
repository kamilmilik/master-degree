package com.example.masterdegree.services.strategyfilters;

import com.example.masterdegree.models.ResultTvPackage;
import com.example.masterdegree.services.PriceService;

import java.util.List;

public class PriceRangeCriteria implements Criteria {
    private PriceService priceService;

    public PriceRangeCriteria(PriceService priceService) {
        this.priceService = priceService;
    }

    @Override
    public List<ResultTvPackage> getFilteredResult(List<ResultTvPackage> resultTvPackages) {
        return priceService.getResultByRangePrice(resultTvPackages);
    }
}
