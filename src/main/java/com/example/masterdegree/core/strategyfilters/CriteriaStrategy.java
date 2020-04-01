package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.dto.ResultTvPackageResponseDto;
import com.example.masterdegree.models.model.ResultTvPackage;
import com.example.masterdegree.models.model.ResultTvPackages;

import java.util.List;

public interface CriteriaStrategy {
    List<ResultTvPackage> getFilteredResult(List<ResultTvPackage> resultTvPackage);
}
