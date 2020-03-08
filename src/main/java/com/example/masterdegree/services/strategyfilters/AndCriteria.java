package com.example.masterdegree.services.strategyfilters;

import com.example.masterdegree.models.ResultTvPackage;

import java.util.List;

public class AndCriteria implements Criteria {

    private Criteria[] criterias;

    public AndCriteria(Criteria... criterias) {
        this.criterias = criterias;
    }

    @Override
    public List<ResultTvPackage> getFilteredResult(List<ResultTvPackage> resultTvPackages) {
        List<ResultTvPackage> filteredResultTvPackages = resultTvPackages;
        for (Criteria criteria : criterias) {
            filteredResultTvPackages = criteria.getFilteredResult(filteredResultTvPackages);
        }
        return filteredResultTvPackages;
    }
}
