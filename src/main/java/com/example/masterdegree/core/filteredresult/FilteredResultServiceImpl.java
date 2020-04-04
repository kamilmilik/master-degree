package com.example.masterdegree.core.filteredresult;

import com.example.masterdegree.models.dto.CriteriaRequestDto;
import com.example.masterdegree.models.dto.ResultTvPackagesResponseDto;
import com.example.masterdegree.models.model.Criteria;
import com.example.masterdegree.models.model.MainTvPackage;
import com.example.masterdegree.models.model.Operator;
import com.example.masterdegree.core.strategyfilters.*;
import com.example.masterdegree.models.mappers.CriteriaMapper;
import com.example.masterdegree.models.mappers.ResultTvPackagesMapper;
import com.example.masterdegree.models.model.filter.FilteredTvPackage;
import com.example.masterdegree.models.model.filter.ResultTvPackage;
import com.example.masterdegree.models.model.filter.ResultTvPackages;
import com.example.masterdegree.repositories.OperatorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FilteredResultServiceImpl implements FilteredResultService {

    private final OperatorsRepository operatorsRepository;
    private final CriteriaMapper criteriaMapper;
    private final ResultTvPackagesMapper resultTvPackagesMapper;


    @Override
    public ResultTvPackagesResponseDto getFilteredResult(CriteriaRequestDto criteriaRequestDto) {
        Criteria criteria = criteriaMapper.convertToEntity(criteriaRequestDto);
        List<ResultTvPackage> resultTvPackages = getFilteredResults(criteria);
        return resultTvPackagesMapper.convertToDto(new ResultTvPackages(resultTvPackages));
    }

    private List<ResultTvPackage> getFilteredResults(Criteria criteria) {
        List<ResultTvPackage> resultTvPackages = createResultWithoutFilters();
        // Filter order: operators -> term -> channels -> price
        CriteriaStrategy andCriteria = AndCriteria.builder()
                .criteria(new OperatorCriteriaStrategy(criteria))
                .criteria(new PriceRangeCriteriaStrategy(criteria))
                .criteria(new ChannelCriteriaStrategy(criteria))
                .build();
        return andCriteria.getFilteredResult(resultTvPackages);

    }

    public List<ResultTvPackage> createResultWithoutFilters() {
        List<ResultTvPackage> resultTvPackages = new LinkedList<>(); // LinkedList since it is faster using it. Removing element O(1), in ArrayList O(n).
        for (Operator operator : operatorsRepository.findAll()) {
            for (MainTvPackage tvPackage : operator.getTvPackages()) {
                FilteredTvPackage filteredTvPackage = new FilteredTvPackage(tvPackage, new LinkedList<>(), tvPackage.getExtraTvPackages());
                resultTvPackages.add(new ResultTvPackage(operator.getId(), operator.getName(), operator.getImgSrc(), filteredTvPackage));
            }
        }
        return resultTvPackages;
    }

}
