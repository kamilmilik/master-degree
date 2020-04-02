package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.model.filter.ResultTvPackage;

import java.util.List;

public interface CriteriaStrategy {
    List<ResultTvPackage> getFilteredResult(List<ResultTvPackage> resultTvPackage);
}
