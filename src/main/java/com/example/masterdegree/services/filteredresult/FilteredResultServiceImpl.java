package com.example.masterdegree.services.filteredresult;

import com.example.masterdegree.models.dto.FilteredTvPackage;
import com.example.masterdegree.models.dto.ResultTvPackage;
import com.example.masterdegree.models.dto.ResultTvPackages;
import com.example.masterdegree.models.entity.MainTvPackage;
import com.example.masterdegree.models.entity.Operator;
import com.example.masterdegree.services.channel.ChannelsService;
import com.example.masterdegree.services.operator.OperatorsService;
import com.example.masterdegree.services.price.PriceService;
import com.example.masterdegree.services.strategyfilters.*;
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
        System.out.println("createResultWithoutFilters size " + operatorsService.getAllOperatorsFromDb().size());
        List<ResultTvPackage> resultTvPackages = new ArrayList<>();
        for (Operator operator : operatorsService.getAllOperatorsFromDb()) {
            for (MainTvPackage tvPackage : operator.getTvPackages()) {
                FilteredTvPackage filteredTvPackage = new FilteredTvPackage(tvPackage, new ArrayList<>(), tvPackage.getExtraTvPackages());
                resultTvPackages.add(new ResultTvPackage(operator.get_id(), operator.getName(), operator.getImgSrc(), filteredTvPackage));
            }
        }
        return resultTvPackages;
    }

    @Override
    public ResultTvPackages getFilteredResult() {
        List<ResultTvPackage> resultTvPackages = createResultWithoutFilters();
        CriteriaStrategy filterCriteria;
        // Filter order: operators -> term -> channels -> price
        if (operatorsService.isAnyOperatorSelected() && !channelsService.isAnyChannelSelected()) {
            filterCriteria = new AndCriteriaStrategy(new OperatorCriteriaStrategy(operatorsService), new PriceRangeCriteriaStrategy(priceService));
        }else if(operatorsService.isAnyOperatorSelected() && channelsService.isAnyChannelSelected()){
            filterCriteria = new AndCriteriaStrategy(new OperatorCriteriaStrategy(operatorsService),new ChannelCriteriaStrategy(channelsService), new PriceRangeCriteriaStrategy(priceService));
        } else if(!operatorsService.isAnyOperatorSelected() && channelsService.isAnyChannelSelected()){
            filterCriteria = new AndCriteriaStrategy(new ChannelCriteriaStrategy(channelsService), new PriceRangeCriteriaStrategy(priceService));
        }
        else {
            filterCriteria = new AndCriteriaStrategy(new PriceRangeCriteriaStrategy(priceService));
        }
        resultTvPackages = filterCriteria.getFilteredResult(resultTvPackages);

        return new ResultTvPackages(resultTvPackages);
    }


}
