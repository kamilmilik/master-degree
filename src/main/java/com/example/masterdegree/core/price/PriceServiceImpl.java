package com.example.masterdegree.core.price;

import com.example.masterdegree.models.dto.ResultTvPackageResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PriceServiceImpl implements PriceService {

    @Override
    public List<ResultTvPackageResponseDto> getResultFilteredByRangePriceInAllTvPackages(List<ResultTvPackageResponseDto> resultTvPackageResponseDtos) {
//        for (ResultTvPackageResponseDto resultTvPackage : resultTvPackageResponseDtos) {
//            for (TvPackage extraAvailableTvPackage : resultTvPackage.getFilteredTvPackageResponseDto().getExtraAvailableTvPackages()) {
//                if (selectedRangePrice.isBetween(extraAvailableTvPackage.getPrice() + resultTvPackage.getFilteredTvPackageResponseDto().getPrice())) {
//                    resultTvPackage.getFilteredTvPackageResponseDto().getExtraTvPackagesWhichMeetCriteria().add(extraAvailableTvPackage);
//                }
//            }
//        }
        return resultTvPackageResponseDtos;
    }


}
