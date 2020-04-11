package com.example.masterdegree.models.model.filter;

import lombok.*;

import java.util.Collections;
import java.util.List;


@AllArgsConstructor
@EqualsAndHashCode
public class ResultTvPackages {

    @Getter(AccessLevel.NONE)
    private List<ResultTvPackage> resultTvPackages;

    public List<ResultTvPackage> getResultTvPackages() {
        return Collections.unmodifiableList(resultTvPackages);
    }
}
