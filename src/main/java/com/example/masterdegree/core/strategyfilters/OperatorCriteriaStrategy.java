package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.dto.ResultTvPackageResponseDto;
import com.example.masterdegree.models.entity.Criteria;
import com.example.masterdegree.models.entity.Operator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OperatorCriteriaStrategy implements CriteriaStrategy {

    private Criteria criteria;

    public OperatorCriteriaStrategy(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public List<ResultTvPackageResponseDto> getFilteredResult(List<ResultTvPackageResponseDto> resultTvPackageResponseDtos) {
        return getResultBySelectedOperators(resultTvPackageResponseDtos);
    }
    public List<ResultTvPackageResponseDto> getResultBySelectedOperators(List<ResultTvPackageResponseDto> resultTvPackageResponseDtos) {
        Set<String> objectIdSet = new HashSet<>(criteria.getOperatorsId());
        resultTvPackageResponseDtos = resultTvPackageResponseDtos.stream().filter(resultTvPackage -> objectIdSet.contains(resultTvPackage.getOperatorId())).collect(Collectors.toList());

        return resultTvPackageResponseDtos;
    }
}
