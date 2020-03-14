package com.example.masterdegree.services.strategyfilters;

import com.example.masterdegree.models.dto.ResultTvPackage;

import java.util.List;

public interface CriteriaStrategy {
    List<ResultTvPackage> getFilteredResult(List<ResultTvPackage> resultTvPackages);
}
