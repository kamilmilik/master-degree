package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.dto.ResultTvPackageResponseDto;

import java.util.List;

public interface CriteriaStrategy {
    List<ResultTvPackageResponseDto> getFilteredResult(List<ResultTvPackageResponseDto> resultTvPackageResponseDtos);
}
