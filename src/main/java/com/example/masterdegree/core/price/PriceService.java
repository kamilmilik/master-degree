package com.example.masterdegree.core.price;

import com.example.masterdegree.models.dto.ResultTvPackage;

import java.util.List;

public interface PriceService {

    void setSelectedRangePrice(double[] price);

    List<ResultTvPackage> getResultFilteredByRangePriceInMainTvPackages(List<ResultTvPackage> resultTvPackages);

    List<ResultTvPackage> getResultFilteredByRangePriceInAllTvPackages(List<ResultTvPackage> resultTvPackages);
}
