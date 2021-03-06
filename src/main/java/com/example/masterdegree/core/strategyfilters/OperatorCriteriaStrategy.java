package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.model.Criteria;
import com.example.masterdegree.models.model.filter.ResultTvPackage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OperatorCriteriaStrategy implements CriteriaStrategy {

    private final Criteria criteria;

    public OperatorCriteriaStrategy(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public List<ResultTvPackage> getFilteredResult(List<ResultTvPackage> resultTvPackages) {
        return getResultBySelectedOperators(resultTvPackages);
    }
    private List<ResultTvPackage> getResultBySelectedOperators(List<ResultTvPackage> resultTvPackages) {
        if(!criteria.getOperatorsId().isEmpty()){ // Empty operators id means that all operators are in criteria.
            Set<String> objectIdSet = new HashSet<>(criteria.getOperatorsId());
            resultTvPackages = resultTvPackages.stream().filter(resultTvPackage -> objectIdSet.contains(resultTvPackage.getOperatorId())).collect(Collectors.toList());
        }

        return resultTvPackages;
    }
}
