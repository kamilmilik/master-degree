package com.example.masterdegree.models.dto;

import com.example.masterdegree.models.entity.TvPackage;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FilteredTvPackageResponseDto extends TvPackageResponseDto {

    private List<TvPackageResponseDto> extraTvPackagesWhichMeetCriteria;

    private List<TvPackageResponseDto> extraAvailableTvPackages;

    public FilteredTvPackageResponseDto(TvPackageResponseDto tvPackage, List<TvPackageResponseDto> extraTvPackagesWhichMeetCriteria, List<TvPackageResponseDto> extraAvailableTvPackages) {
        super(tvPackage.getName(), tvPackage.getDescription(), tvPackage.getPrice(), tvPackage.getType(), tvPackage.getImgSrc(), tvPackage.getLink(), tvPackage.getTerm(), tvPackage.getStatus(), tvPackage.getChannels());
        this.extraTvPackagesWhichMeetCriteria = extraTvPackagesWhichMeetCriteria;
        this.extraAvailableTvPackages = extraAvailableTvPackages;
    }
}
