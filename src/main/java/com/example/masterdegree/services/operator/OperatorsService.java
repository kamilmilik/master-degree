package com.example.masterdegree.services.operator;

import com.example.masterdegree.models.entity.Operator;
import com.example.masterdegree.models.dto.ResultTvPackage;

import java.util.List;
import java.util.Set;

public interface OperatorsService {

    void addFetchedOperatorResourceToFetchedList(Operator operator);

    void removeFetchedOperatorResourceFromList(Operator operator);

    Set<Operator> getFetchedSelectedOperators();

    boolean isAnyOperatorSelected();

    List<Operator> getAllOperatorsFromDb();

    List<ResultTvPackage> getResultBySelectedOperators(List<ResultTvPackage> resultTvPackages);
}
