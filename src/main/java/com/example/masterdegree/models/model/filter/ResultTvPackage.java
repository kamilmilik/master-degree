package com.example.masterdegree.models.model.filter;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@ToString()
public class ResultTvPackage {

    @NonNull
    @Getter
    private String operatorId;
    @NonNull
    @Getter
    private String operatorName;
    @Getter
    private String operatorImg;
    @NonNull
    @Getter
    private FilteredTvPackage filteredTvPackage;

}
