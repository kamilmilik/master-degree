package com.example.masterdegree.models.model.filter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;


@AllArgsConstructor
public class ResultTvPackages {

    @Getter(AccessLevel.NONE)
    private List<ResultTvPackage> resultTvPackages;

    public List<ResultTvPackage> getResultTvPackages() {
        return Collections.unmodifiableList(resultTvPackages);
    }
}
