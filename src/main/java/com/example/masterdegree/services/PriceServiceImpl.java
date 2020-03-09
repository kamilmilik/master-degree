package com.example.masterdegree.services;

import com.example.masterdegree.models.ResultTvPackage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {
    private double[] selectedRangePrice;

    @Override
    public void setSelectedRangePrice(double[] price) {
        this.selectedRangePrice = price;
    }

    @Override
    public double getMinSelectedPrice() {
        return chooseMinimumPrice();
    }

    @Override
    public double getMaxSelectedPrice() {
        return chooseMaximumPrice();
    }

    private double chooseMinimumPrice() {
        return Math.min(selectedRangePrice[0], selectedRangePrice[1]);
    }

    private double chooseMaximumPrice() {
        return Math.max(selectedRangePrice[0], selectedRangePrice[1]);
    }

    @Override
    public List<ResultTvPackage> getResultByRangePrice(List<ResultTvPackage> resultTvPackages) {
        resultTvPackages = resultTvPackages.stream().
                filter(resultTvPackage -> isTvPackagePriceInSelectedRangePrice(resultTvPackage.getMainTPackage().getPrice()))
                .collect(Collectors.toList());
        return resultTvPackages;
    }

    private boolean isTvPackagePriceInSelectedRangePrice(double tvPackagePrice) {
        return getMinSelectedPrice() <= tvPackagePrice && tvPackagePrice <= getMaxSelectedPrice();
    }
}
