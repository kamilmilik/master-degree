package com.example.masterdegree.core.operator;

import com.example.masterdegree.models.dto.OperatorDto;
import com.example.masterdegree.models.dto.ResultTvPackages;
import com.example.masterdegree.models.entity.MainTvPackage;
import com.example.masterdegree.models.entity.Operator;
import com.example.masterdegree.models.dto.ResultTvPackage;
import com.example.masterdegree.models.entity.TvPackage;

import java.util.List;
import java.util.Set;

public interface OperatorsService {

    void addFetchedOperatorToFetchedList(Operator operator);

    void removeFetchedOperatorFromFetchedList(Operator operator);

    Set<Operator> getFetchedSelectedOperators();

    boolean isAnyOperatorSelected();

    List<OperatorDto> getOperators();

    List<Operator> getAllOperatorsFromDb();

    List<MainTvPackage> getMainTvPackagesByIdOperator(String id);

    ResultTvPackages getFilteredTvPackagesByOperatorId(String id);

    Operator getOperatorById(OperatorDto operatorDto);

    List<ResultTvPackage> getResultBySelectedOperators(List<ResultTvPackage> resultTvPackages);
}
