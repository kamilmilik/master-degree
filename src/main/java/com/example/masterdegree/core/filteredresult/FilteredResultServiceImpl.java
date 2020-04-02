package com.example.masterdegree.core.filteredresult;

import com.example.masterdegree.models.dto.CriteriaRequestDto;
import com.example.masterdegree.models.dto.ResultTvPackagesResponseDto;
import com.example.masterdegree.models.entity.Criteria;
import com.example.masterdegree.models.entity.MainTvPackage;
import com.example.masterdegree.models.entity.Operator;
import com.example.masterdegree.core.operator.OperatorsService;
import com.example.masterdegree.core.strategyfilters.*;
import com.example.masterdegree.models.mappers.CriteriaMapper;
import com.example.masterdegree.models.mappers.ResultTvPackagesMapper;
import com.example.masterdegree.models.model.FilteredTvPackage;
import com.example.masterdegree.models.model.ResultTvPackage;
import com.example.masterdegree.models.model.ResultTvPackages;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FilteredResultServiceImpl implements FilteredResultService {

    private final OperatorsService operatorsService;
    private final CriteriaMapper criteriaMapper;
    private final ResultTvPackagesMapper resultTvPackagesMapper;


    @Override
    public ResultTvPackagesResponseDto getFilteredResult(CriteriaRequestDto criteriaRequestDto) {
        Criteria criteria = criteriaMapper.convertToEntity(criteriaRequestDto);
        List<ResultTvPackage> resultTvPackages = createResultWithoutFilters();
        // Filter order: operators -> term -> channels -> price
        CriteriaStrategy andCriteria = AndCriteria.builder()
                .criteria(new OperatorCriteriaStrategy(criteria))
                .criteria(new PriceRangeCriteriaStrategy(criteria))
                .criteria(new ChannelCriteriaStrategy(criteria))
                .build();
        resultTvPackages = andCriteria.getFilteredResult(resultTvPackages);
        return resultTvPackagesMapper.convertToDto(new ResultTvPackages(resultTvPackages));
    }

    public List<ResultTvPackage> createResultWithoutFilters() {
        List<ResultTvPackage> resultTvPackages = new ArrayList<>();
        for (Operator operator : operatorsService.getAllOperatorsFromDb()) {
            for (MainTvPackage tvPackage : operator.getTvPackages()) {
                FilteredTvPackage filteredTvPackage = new FilteredTvPackage(tvPackage, new ArrayList<>(), tvPackage.getExtraTvPackages());
                resultTvPackages.add(new ResultTvPackage(operator.getId(), operator.getName(), operator.getImgSrc(), filteredTvPackage));
            }
        }
        return resultTvPackages;
    }

}
