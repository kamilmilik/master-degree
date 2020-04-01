package com.example.masterdegree.core.filteredresult;

import com.example.masterdegree.models.dto.ResultTvPackagesResponseDto;
import com.example.masterdegree.models.entity.Criteria;
import com.example.masterdegree.models.model.ResultTvPackages;

public interface FilteredResultService {

    ResultTvPackages getFilteredResult(Criteria criteria);

}
