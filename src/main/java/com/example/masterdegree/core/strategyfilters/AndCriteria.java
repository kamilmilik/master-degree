package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.dto.ResultTvPackageResponseDto;
import lombok.Builder;
import lombok.Singular;

import java.util.List;

@Builder
public class AndCriteria implements CriteriaStrategy {

    @Singular
    private List<CriteriaStrategy> criterias;

    @Override
    public List<ResultTvPackageResponseDto> getFilteredResult(List<ResultTvPackageResponseDto> resultTvPackageResponseDtos) {
        List<ResultTvPackageResponseDto> filteredResultTvPackageResponseDtos = resultTvPackageResponseDtos;
        for (CriteriaStrategy criteria : criterias) {
            filteredResultTvPackageResponseDtos = criteria.getFilteredResult(filteredResultTvPackageResponseDtos);
        }
        return filteredResultTvPackageResponseDtos;
    }
}
