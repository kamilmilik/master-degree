package com.example.masterdegree.services.strategyfilters;

import com.example.masterdegree.models.ResultTvPackage;

import java.util.List;

public interface Criteria {
    List<ResultTvPackage> getFilteredResult(List<ResultTvPackage> resultTvPackages);
}
