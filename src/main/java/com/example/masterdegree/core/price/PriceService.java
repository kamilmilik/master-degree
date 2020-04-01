package com.example.masterdegree.core.price;

import com.example.masterdegree.models.dto.ResultTvPackageResponseDto;

import java.util.List;

public interface PriceService {

    List<ResultTvPackageResponseDto> getResultFilteredByRangePriceInAllTvPackages(List<ResultTvPackageResponseDto> resultTvPackageResponseDtos);
}
