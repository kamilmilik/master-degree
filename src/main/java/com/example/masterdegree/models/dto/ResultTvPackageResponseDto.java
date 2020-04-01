package com.example.masterdegree.models.dto;

import lombok.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode()
@ToString()
public class ResultTvPackageResponseDto {

    @NonNull
    private String operatorId;
    @NonNull
    private String operatorName;
    private String operatorImg;
    @NonNull
    private FilteredTvPackageResponseDto filteredTvPackageResponseDto;

}
