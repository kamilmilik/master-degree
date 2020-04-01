package com.example.masterdegree.core.filteredresult;

import com.example.masterdegree.models.dto.FilteredTvPackageResponseDto;
import com.example.masterdegree.models.dto.ResultTvPackageResponseDto;
import com.example.masterdegree.models.dto.ResultTvPackagesResponseDto;
import com.example.masterdegree.models.entity.Criteria;
import com.example.masterdegree.models.entity.MainTvPackage;
import com.example.masterdegree.models.entity.Operator;
import com.example.masterdegree.core.channel.ChannelsService;
import com.example.masterdegree.core.operator.OperatorsService;
import com.example.masterdegree.core.price.PriceService;
import com.example.masterdegree.core.strategyfilters.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FilteredResultServiceImpl implements FilteredResultService {

    private final OperatorsService operatorsService;
    private final PriceService priceService;
    private final ChannelsService channelsService;

    @Override
    public ResultTvPackagesResponseDto getFilteredResult(Criteria criteria) {
        List<ResultTvPackageResponseDto> resultTvPackageResponseDtos = createResultWithoutFilters();
        // Filter order: operators -> term -> channels -> price
        CriteriaStrategy andCriteria = AndCriteria.builder()
                .criteria(new OperatorCriteriaStrategy(criteria))
                .criteria(new PriceRangeCriteriaStrategy(criteria))
                .criteria(new ChannelCriteriaStrategy(criteria))
                .build();
        resultTvPackageResponseDtos = andCriteria.getFilteredResult(resultTvPackageResponseDtos);
        return new ResultTvPackagesResponseDto(resultTvPackageResponseDtos);
    }

    public List<ResultTvPackageResponseDto> createResultWithoutFilters() {
        System.out.println("createResultWithoutFilters size " + operatorsService.getOperators().size());
        List<ResultTvPackageResponseDto> resultTvPackageResponseDtos = new ArrayList<>();
        for (Operator operator : operatorsService.getAllOperatorsFromDb()) {
            for (MainTvPackage tvPackage : operator.getTvPackages()) {
                FilteredTvPackageResponseDto filteredTvPackageResponseDto = new FilteredTvPackageResponseDto(tvPackage, new ArrayList<>(), tvPackage.getExtraTvPackages());
                resultTvPackageResponseDtos.add(new ResultTvPackageResponseDto(operator.getId(), operator.getName(), operator.getImgSrc(), filteredTvPackageResponseDto));
            }
        }
        return resultTvPackageResponseDtos;
    }


}
