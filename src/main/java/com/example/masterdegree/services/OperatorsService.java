package com.example.masterdegree.services;

import com.example.masterdegree.models.Operator;
import com.example.masterdegree.models.ResultTvPackage;
import com.example.masterdegree.models.TvPackage;

import java.util.List;

public interface OperatorsService {

    void addFetchedOperatorResourceToFetchedList(Operator operator);

    void removeFetchedOperatorResourceFromList(Operator operator);

    List<Operator> getFetchedOperators();

    boolean isAnyOperatorSelected();

    List<Operator> getAllOperatorsFromDb();

    List<ResultTvPackage> getResultByFetchedOperators(List<ResultTvPackage> resultTvPackages);
}
