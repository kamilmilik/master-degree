package com.example.masterdegree.models.model.filter;

import com.example.masterdegree.models.model.TvPackage;
import lombok.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FilteredTvPackage extends TvPackage {

    private List<TvPackage> extraTvPackagesWhichMeetCriteria;
    @Getter(AccessLevel.NONE)
    private List<TvPackage> extraAvailableTvPackages;

    public FilteredTvPackage(TvPackage tvPackage, List<TvPackage> extraTvPackagesWhichMeetCriteria, List<TvPackage> extraAvailableTvPackages) {
        super(tvPackage.getName(), tvPackage.getDescription(), tvPackage.getPrice(), tvPackage.getType(), tvPackage.getImgSrc(), tvPackage.getLink(), tvPackage.getTerm(), tvPackage.getStatus(), tvPackage.getChannels());
        this.extraTvPackagesWhichMeetCriteria = extraTvPackagesWhichMeetCriteria;
        this.extraAvailableTvPackages = extraAvailableTvPackages;
    }

    public List<TvPackage> getExtraAvailableTvPackages() { // Getter for mapper.
        return Collections.unmodifiableList(extraAvailableTvPackages);
    }

    public boolean hasExtraTvPackagesWhichMeetCriteria() {
        return extraTvPackagesWhichMeetCriteria.size() > 0;
    }

    public void replaceExtraTvPackagesWithGivenList(List<TvPackage> listToReplace) {
        extraTvPackagesWhichMeetCriteria = new LinkedList<>();
        extraTvPackagesWhichMeetCriteria.addAll(listToReplace);
    }

    public void removeGivenTvPackagesFromExtraTvPackages(List<TvPackage> tvPackages) {
        extraAvailableTvPackages.removeAll(tvPackages);
    }

    public void removeGivenTvPackageFromExtraTvPackagesIfContainsAllChannels(List<TvPackage> tvPackages){
        tvPackages.forEach(meetCriteriaTvPackage -> {
            extraAvailableTvPackages.removeIf(tvPackage ->
                    meetCriteriaTvPackage.getChannels().containsAll(tvPackage.getChannels())
            );
        });
    }

}
