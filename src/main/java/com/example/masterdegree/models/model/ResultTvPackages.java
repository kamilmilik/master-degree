package com.example.masterdegree.models.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
public class ResultTvPackages {
    @Getter
    private List<ResultTvPackage> resultTvPackages;

}
