package com.example.masterdegree.models.model.filter;

import lombok.*;


@Getter
@AllArgsConstructor
@EqualsAndHashCode()
@ToString()
public class ResultTvPackage {

    @NonNull
    private final String operatorId;
    @NonNull
    private final String operatorName;
    private final String operatorImg;
    @NonNull
    private final FilteredTvPackage filteredTvPackage;

}
