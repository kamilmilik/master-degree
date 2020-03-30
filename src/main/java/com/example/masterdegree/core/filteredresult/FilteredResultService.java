package com.example.masterdegree.core.filteredresult;

import com.example.masterdegree.models.dto.CriteriaDto;
import com.example.masterdegree.models.dto.ResultTvPackage;
import com.example.masterdegree.models.dto.ResultTvPackages;
import com.example.masterdegree.models.entity.Operator;

import java.util.List;

public interface FilteredResultService {

    ResultTvPackages getFilteredResult();

    ResultTvPackages getFilteredResult(CriteriaDto criteria);

    List<ResultTvPackage> createFilteredTvPackagesByOperator(Operator operator);

}
