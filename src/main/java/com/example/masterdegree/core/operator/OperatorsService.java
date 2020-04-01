package com.example.masterdegree.core.operator;

import com.example.masterdegree.models.dto.OperatorRequestDto;
import com.example.masterdegree.models.dto.ResultTvPackageResponseDto;
import com.example.masterdegree.models.dto.ResultTvPackagesResponseDto;
import com.example.masterdegree.models.entity.MainTvPackage;
import com.example.masterdegree.models.entity.Operator;

import java.util.List;
import java.util.Set;

public interface OperatorsService {

    void addFetchedOperatorToFetchedList(Operator operator);

    void removeFetchedOperatorFromFetchedList(Operator operator);

    Set<Operator> getFetchedSelectedOperators();

    boolean isAnyOperatorSelected();

    List<OperatorRequestDto> getOperators();

    List<Operator> getAllOperatorsFromDb();

    List<MainTvPackage> getMainTvPackagesByIdOperator(String id);

    ResultTvPackagesResponseDto getFilteredTvPackagesByOperatorId(String id);

    Operator getOperatorById(OperatorRequestDto operatorRequestDto);

    List<ResultTvPackageResponseDto> getResultBySelectedOperators(List<ResultTvPackageResponseDto> resultTvPackageResponseDtos);
}
