package com.example.masterdegree.core.filteredresult;

import com.example.masterdegree.core.strategyfilters.*;
import com.example.masterdegree.models.dto.CriteriaRequestDto;
import com.example.masterdegree.models.mappers.CriteriaMapper;
import com.example.masterdegree.models.model.Criteria;
import com.example.masterdegree.models.model.MainTvPackage;
import com.example.masterdegree.models.model.Operator;
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
public class FilteredResultService {

    private final OperatorsRepository operatorsRepository;

    public ResultTvPackages getFilteredResult(CriteriaRequestDto criteriaRequestDto) {
        CriteriaMapper criteriaMapper = new CriteriaMapper();
        Criteria criteria = criteriaMapper.convertToEntity(criteriaRequestDto);

        List<ResultTvPackage> resultTvPackages = getFilteredResults(criteria);

        return new ResultTvPackages(resultTvPackages);
    }

    private List<ResultTvPackage> getFilteredResults(Criteria criteria) {
        List<ResultTvPackage> resultTvPackages = createResultWithoutFilters();

        return filterByCriteria(criteria, resultTvPackages);
    }

    private List<ResultTvPackage> filterByCriteria(Criteria criteria, List<ResultTvPackage> resultTvPackages){
        // Filter order: operators -> term -> channels -> price
        CriteriaStrategy andCriteria = AndCriteria.builder()
                .criteriaStrategy(new OperatorCriteriaStrategy(criteria))
                .criteriaStrategy(new TermCriteriaStrategy(criteria))
                .criteriaStrategy(new PriceCriteriaStrategy(criteria))
                .criteriaStrategy(new ChannelCriteriaStrategy(criteria))
                .criteriaStrategy(new ChannelAndPriceCombinationCriteriaStrategy(criteria))
                .build();

        return andCriteria.getFilteredResult(resultTvPackages);
    }

    public List<ResultTvPackage> createResultWithoutFilters() {
        List<ResultTvPackage> resultTvPackages = new LinkedList<>(); // LinkedList since it is faster using it. Removing element O(1), in ArrayList O(n).
        for (Operator operator : operatorsRepository.findAll()) {
            for (MainTvPackage tvPackage : operator.getTvPackages()) {
                FilteredTvPackage filteredTvPackage = new FilteredTvPackage(tvPackage, new LinkedList<>(), new LinkedList<>(tvPackage.getExtraTvPackages()));
                resultTvPackages.add(new ResultTvPackage(operator.getId(), operator.getName(), operator.getImgSrc(), filteredTvPackage));
            }
        }
        return resultTvPackages;
    }
}
