package com.example.masterdegree.models.model;

import com.example.masterdegree.models.entity.TvPackage;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FilteredTvPackage extends TvPackage {

    @Getter
    private List<TvPackage> extraTvPackagesWhichMeetCriteria;
    @Getter
    private List<TvPackage> extraAvailableTvPackages;

    public FilteredTvPackage(TvPackage tvPackage, List<TvPackage> extraTvPackagesWhichMeetCriteria, List<TvPackage> extraAvailableTvPackages) {
        super(tvPackage.getName(), tvPackage.getDescription(), tvPackage.getPrice(), tvPackage.getType(), tvPackage.getImgSrc(), tvPackage.getLink(), tvPackage.getTerm(), tvPackage.getStatus(), tvPackage.getChannels());
        this.extraTvPackagesWhichMeetCriteria = extraTvPackagesWhichMeetCriteria;
        this.extraAvailableTvPackages = extraAvailableTvPackages;
    }
}
