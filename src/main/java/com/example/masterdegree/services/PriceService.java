package com.example.masterdegree.services;

import com.example.masterdegree.models.Operator;
import com.example.masterdegree.models.ResultTvPackage;

import java.util.List;

public interface PriceService {

    void setSelectedRangePrice(double[] price);

    double getMaxSelectedPrice();

    double getMinSelectedPrice();

    List<ResultTvPackage> getResultByRangePrice(List<ResultTvPackage> resultTvPackages);
}
