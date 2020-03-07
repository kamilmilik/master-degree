package com.example.masterdegree.models;

import java.util.List;

public class Result {

    private List<ResultTvPackage> resultTvPackages;

    public Result(List<ResultTvPackage> resultTvPackages) {
        this.resultTvPackages = resultTvPackages;
    }

    public List<ResultTvPackage> getResultTvPackages() {
        return resultTvPackages;
    }

}
