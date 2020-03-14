package com.example.masterdegree.services.price;

import com.example.masterdegree.models.dto.ResultTvPackage;
import com.example.masterdegree.models.entity.TvPackage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
    public List<ResultTvPackage> getResultFilteredByRangePriceInMainTvPackages(List<ResultTvPackage> resultTvPackages) {
        resultTvPackages = resultTvPackages.stream().
                filter(resultTvPackage -> isTvPackagePriceInSelectedRangePrice(resultTvPackage.getFilteredTvPackage().getPrice()))
                .collect(Collectors.toList());

        return resultTvPackages;
    }

    @Override
    public List<ResultTvPackage> getResultFilteredByRangePriceInAllTvPackages(List<ResultTvPackage> resultTvPackages) {
        for (ResultTvPackage resultTvPackage : resultTvPackages) {
            for (TvPackage extraAvailableTvPackage : resultTvPackage.getFilteredTvPackage().getExtraAvailableTvPackages()) {
                if (isTvPackagePriceInSelectedRangePrice(extraAvailableTvPackage.getPrice() + resultTvPackage.getFilteredTvPackage().getPrice())) {
                    resultTvPackage.getFilteredTvPackage().getExtraTvPackagesWhichMeetCriteria().add(extraAvailableTvPackage);
                }
            }
        }
        return resultTvPackages;
    }

    private boolean isTvPackagePriceInSelectedRangePrice(double tvPackagePrice) {
        return getMinSelectedPrice() <= tvPackagePrice && tvPackagePrice <= getMaxSelectedPrice();
    }
}
