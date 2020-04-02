package com.example.masterdegree.models.model.filter;

import lombok.*;


@Getter
@AllArgsConstructor
@EqualsAndHashCode()
@ToString()
public class ResultTvPackage {

    @NonNull
    private String operatorId;
    @NonNull
    private String operatorName;
    private String operatorImg;
    @NonNull
    private FilteredTvPackage filteredTvPackage;

}
