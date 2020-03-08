package com.example.masterdegree.services;

import com.example.masterdegree.models.Operator;
import com.example.masterdegree.models.Result;
import com.example.masterdegree.models.ResultTvPackage;
import com.example.masterdegree.models.TvPackage;
import com.example.masterdegree.services.strategyfilters.AndCriteria;
import com.example.masterdegree.services.strategyfilters.Criteria;
import com.example.masterdegree.services.strategyfilters.OperatorCriteria;
import com.example.masterdegree.services.strategyfilters.PriceRangeCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    private OperatorsService operatorsService;

    private PriceService priceService;

    @Autowired
    public ResultServiceImpl(OperatorsService operatorsService, PriceService priceService) {
        this.operatorsService = operatorsService;
        this.priceService = priceService;
    }

    public List<ResultTvPackage> createResultWithoutFilters() {
        System.out.println("createResultWithoutFilters size " + operatorsService.getAllOperatorsFromDb().size());
        List<ResultTvPackage> resultTvPackages = new ArrayList<>();
        for (Operator operator : operatorsService.getAllOperatorsFromDb()) {
            for (TvPackage tvPackage : operator.getTvPackages()) {
                resultTvPackages.add(new ResultTvPackage(operator.getId(), operator.getName(), operator.getImgSrc(), tvPackage));
            }
        }
        return resultTvPackages;
    }

    @Override
    public Result getResult() {
        List<ResultTvPackage> resultTvPackages = createResultWithoutFilters();
        Criteria filterCriteria;
        if (operatorsService.isAnyOperatorSelected()) {
            filterCriteria = new AndCriteria(new OperatorCriteria(operatorsService), new PriceRangeCriteria(priceService));
        } else {
            filterCriteria = new AndCriteria(new PriceRangeCriteria(priceService));
        }
        resultTvPackages = filterCriteria.getFilteredResult(resultTvPackages);

        return new Result(resultTvPackages);
    }


}
