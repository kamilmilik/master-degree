package com.example.masterdegree.services;

import com.example.masterdegree.models.Operator;
import com.example.masterdegree.models.Result;
import com.example.masterdegree.models.ResultTvPackage;
import com.example.masterdegree.models.TvPackage;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    private OperatorsService operatorsService;

    @Autowired
    public ResultServiceImpl(OperatorsService operatorsService) {
        this.operatorsService = operatorsService;
    }

    @Override
    public Result getResult() {
        System.out.println("Jestem resultem");
        List<Operator> operators = operatorsService.getFetchedOperators();
        List<ResultTvPackage> resultTvPackages = getResultByOperators(operators);
        return new Result(resultTvPackages);
    }

    public List<ResultTvPackage> getResultByOperators(List<Operator> operators) {
        List<ResultTvPackage> resultTvPackages = new ArrayList<>();
        for (Operator operator : operators) {
            for (TvPackage tvPackage : operatorsService.getTvPackagesByOperator(operator)) {
                resultTvPackages.add(new ResultTvPackage(operator.getName(), operator.getImgSrc(), tvPackage));
            }
        }
        return resultTvPackages;
    }

}
