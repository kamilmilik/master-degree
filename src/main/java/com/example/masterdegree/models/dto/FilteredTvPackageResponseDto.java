package com.example.masterdegree.models.dto;

import com.example.masterdegree.models.entity.TvPackage;
import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FilteredTvPackageResponseDto extends TvPackage {

    private List<TvPackage> extraTvPackagesWhichMeetCriteria;

    private List<TvPackage> extraAvailableTvPackages;

    public FilteredTvPackageResponseDto(TvPackage tvPackage, List<TvPackage> extraTvPackagesWhichMeetCriteria, List<TvPackage> extraAvailableTvPackages) {
        super(tvPackage.getName(), tvPackage.getDescription(), tvPackage.getPrice(), tvPackage.getType(), tvPackage.getImgSrc(), tvPackage.getLink(), tvPackage.getTerm(), tvPackage.getStatus(), tvPackage.getChannels());
        this.extraTvPackagesWhichMeetCriteria = extraTvPackagesWhichMeetCriteria;
        this.extraAvailableTvPackages = extraAvailableTvPackages;
    }
}
