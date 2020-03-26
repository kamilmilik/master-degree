package com.example.masterdegree.models.dto;

import lombok.*;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@EqualsAndHashCode()
@ToString(callSuper = true)
public class ResultTvPackage {

    @NonNull
    private String operatorId;

    @NonNull
    private String operatorName;

    private String operatorImg;

    @NonNull
    private FilteredTvPackage filteredTvPackage;

}
