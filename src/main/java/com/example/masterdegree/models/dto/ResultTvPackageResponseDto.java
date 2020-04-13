package com.example.masterdegree.models.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@ToString()
//TODO KM zamienic na ResultTvPackage z TvPackageDto
public class ResultTvPackageResponseDto {
    @NonNull
    private String operatorId;
    @NonNull
    private String operatorName;
    private String operatorImg;
    @NonNull
    private FilteredTvPackageResponseDto filteredTvPackage;

}
