package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.dto.ResultTvPackageResponseDto;
import com.example.masterdegree.models.entity.Criteria;

import java.util.List;

public class PriceRangeCriteriaStrategy implements CriteriaStrategy {

    private Criteria criteria;

    public PriceRangeCriteriaStrategy(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public List<ResultTvPackageResponseDto> getFilteredResult(List<ResultTvPackageResponseDto> resultTvPackageResponseDtos) {
        return this.getResultFilteredByRangePriceInMainTvPackages(resultTvPackageResponseDtos);
    }

    public List<ResultTvPackageResponseDto> getResultFilteredByRangePriceInMainTvPackages(List<ResultTvPackageResponseDto> resultTvPackageResponseDtos) {
//        resultTvPackageResponseDtos = resultTvPackageResponseDtos.stream().
//                filter(resultTvPackage -> criteria.getPrice().isBetween(resultTvPackage.getFilteredTvPackageResponseDto().getPrice()))
//                .collect(Collectors.toList());

        return resultTvPackageResponseDtos;
    }

}
