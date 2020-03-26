package com.example.masterdegree.core.filteredresult;

import com.example.masterdegree.models.dto.FilteredTvPackage;
import com.example.masterdegree.models.dto.ResultTvPackage;
import com.example.masterdegree.models.dto.ResultTvPackages;
import com.example.masterdegree.models.entity.MainTvPackage;
import com.example.masterdegree.models.entity.Operator;
import com.example.masterdegree.core.channel.ChannelsService;
import com.example.masterdegree.core.operator.OperatorsService;
import com.example.masterdegree.core.price.PriceService;
import com.example.masterdegree.core.strategyfilters.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilteredResultServiceImpl implements FilteredResultService {

    private OperatorsService operatorsService;

    private PriceService priceService;

    private ChannelsService channelsService;

    @Autowired
    public FilteredResultServiceImpl(OperatorsService operatorsService, PriceService priceService, ChannelsService channelsService) {
        this.operatorsService = operatorsService;
        this.priceService = priceService;
        this.channelsService = channelsService;
    }

    public List<ResultTvPackage> createResultWithoutFilters() {
        System.out.println("createResultWithoutFilters size " + operatorsService.getOperators().size());
        List<ResultTvPackage> resultTvPackages = new ArrayList<>();
        for (Operator operator : operatorsService.getAllOperatorsFromDb()) {
            for (MainTvPackage tvPackage : operator.getTvPackages()) {
                FilteredTvPackage filteredTvPackage = new FilteredTvPackage(tvPackage, new ArrayList<>(), tvPackage.getExtraTvPackages());
                resultTvPackages.add(new ResultTvPackage(operator.getId(), operator.getName(), operator.getImgSrc(), filteredTvPackage));
            }
        }
        return resultTvPackages;
    }

    @Override
    public ResultTvPackages getFilteredResult() {
        List<ResultTvPackage> resultTvPackages = createResultWithoutFilters();
        // Filter order: operators -> term -> channels -> price
        CriteriaStrategy filterCriteria = selectFilterCriteria();
        resultTvPackages = filterCriteria.getFilteredResult(resultTvPackages);
        return new ResultTvPackages(resultTvPackages);
    }

    private CriteriaStrategy selectFilterCriteria(){
        if (isSelectedOperatorAndPriceAndChannelsAndTerm()) {
            return  new AndCriteriaStrategy(new OperatorCriteriaStrategy(operatorsService), new ChannelCriteriaStrategy(channelsService), new PriceRangeCriteriaStrategy(priceService));
        } else if (isSelectedOperatorAndPriceAndTerm()) { // Term and price is set default.
            return new AndCriteriaStrategy(new OperatorCriteriaStrategy(operatorsService), new PriceRangeCriteriaStrategy(priceService));
        } else if (isSelectedPriceAndChannelsAndTerm()) {
            return new AndCriteriaStrategy(new ChannelCriteriaStrategy(channelsService), new PriceRangeCriteriaStrategy(priceService));
        } else {
            return new AndCriteriaStrategy(new PriceRangeCriteriaStrategy(priceService));
        }
    }

    private boolean isSelectedOperatorAndPriceAndChannelsAndTerm() {
        return operatorsService.isAnyOperatorSelected() && channelsService.isAnyChannelSelected();
    }

    private boolean isSelectedOperatorAndPriceAndTerm() {
        return operatorsService.isAnyOperatorSelected() && !channelsService.isAnyChannelSelected();
    }

    private boolean isSelectedPriceAndChannelsAndTerm() {
        return !operatorsService.isAnyOperatorSelected() && channelsService.isAnyChannelSelected();
    }

}
