package com.example.masterdegree.core.filteredresult;

import com.example.masterdegree.models.dto.CriteriaRequestDto;
import com.example.masterdegree.models.dto.ResultTvPackagesResponseDto;
import com.example.masterdegree.models.model.ResultTvPackages;

public interface FilteredResultService {

    ResultTvPackagesResponseDto getFilteredResult(CriteriaRequestDto criteriaRequestDto);

}
