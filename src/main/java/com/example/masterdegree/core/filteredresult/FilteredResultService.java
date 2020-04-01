package com.example.masterdegree.core.filteredresult;

import com.example.masterdegree.models.dto.ResultTvPackagesResponseDto;
import com.example.masterdegree.models.entity.Criteria;

public interface FilteredResultService {

    ResultTvPackagesResponseDto getFilteredResult(Criteria criteria);

}
