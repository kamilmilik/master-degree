package com.example.masterdegree.models.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@ToString()
public class ResultTvPackageResponseDto {

    @NonNull
    @Getter
    private String operatorId;
    @NonNull
    private String operatorName;
    private String operatorImg;
    @NonNull
    private FilteredTvPackageResponseDto filteredTvPackage;

}
