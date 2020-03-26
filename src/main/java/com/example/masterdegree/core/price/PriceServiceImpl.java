package com.example.masterdegree.core.price;

import com.example.masterdegree.models.dto.ResultTvPackage;
import com.example.masterdegree.models.entity.TvPackage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PriceServiceImpl implements PriceService {
    private RangePrice selectedRangePrice;

    @Override
    public void setSelectedRangePrice(double[] price) {
        this.selectedRangePrice = new RangePrice(price);
    }

    @Override
    public List<ResultTvPackage> getResultFilteredByRangePriceInMainTvPackages(List<ResultTvPackage> resultTvPackages) {
        resultTvPackages = resultTvPackages.stream().
                filter(resultTvPackage -> selectedRangePrice.isBetween(resultTvPackage.getFilteredTvPackage().getPrice()))
                .collect(Collectors.toList());

        return resultTvPackages;
    }

    @Override
    public List<ResultTvPackage> getResultFilteredByRangePriceInAllTvPackages(List<ResultTvPackage> resultTvPackages) {
        for (ResultTvPackage resultTvPackage : resultTvPackages) {
            for (TvPackage extraAvailableTvPackage : resultTvPackage.getFilteredTvPackage().getExtraAvailableTvPackages()) {
                if (selectedRangePrice.isBetween(extraAvailableTvPackage.getPrice() + resultTvPackage.getFilteredTvPackage().getPrice())) {
                    resultTvPackage.getFilteredTvPackage().getExtraTvPackagesWhichMeetCriteria().add(extraAvailableTvPackage);
                }
            }
        }
        return resultTvPackages;
    }


}
