package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.model.Channel;
import com.example.masterdegree.models.model.Criteria;
import com.example.masterdegree.models.model.filter.ResultTvPackage;

import java.util.List;
import java.util.stream.Collectors;

public class ChannelCriteriaStrategy implements CriteriaStrategy {

    private final Criteria criteria;

    public ChannelCriteriaStrategy(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public List<ResultTvPackage> getFilteredResult(List<ResultTvPackage> resultTvPackages) {
        return this.getResultBySelectedChannels(resultTvPackages);
    }

    private List<ResultTvPackage> getResultBySelectedChannels(List<ResultTvPackage> resultTvPackages) {
        return searchInMainAndExtraTvPackages(resultTvPackages);
    }

    private List<ResultTvPackage> searchInMainAndExtraTvPackages(List<ResultTvPackage> resultTvPackages) {
        for (String selectedChannelName : criteria.getChannelsName()) {
            resultTvPackages.removeIf(resultTvPackage -> {
                boolean isFoundSelectedChannel = searchChannelInMainTvPackage(resultTvPackage, selectedChannelName);
                if (!isFoundSelectedChannel) {
                    isFoundSelectedChannel = searchChannelInExtraAvailablePackages(resultTvPackage, selectedChannelName);
                }
                return !isFoundSelectedChannel;
            });
        }
        return resultTvPackages;
    }

    boolean searchChannelInMainTvPackage(ResultTvPackage resultTvPackage, String searchedChannelName) {
        return resultTvPackage.getFilteredTvPackage().getChannels().stream().anyMatch(channel -> channel.isTheSame(searchedChannelName));
    }

    private boolean searchChannelInExtraAvailablePackages(ResultTvPackage resultTvPackage, String searchedChannelName) {
        return resultTvPackage.getFilteredTvPackage().getExtraTvPackagesWhichMeetCriteria().addAll(
                resultTvPackage.getFilteredTvPackage().getExtraAvailableTvPackages().stream()
                        .filter(tvPackage -> tvPackage.getChannels().stream()
                                .anyMatch(channel -> channel.isTheSame(searchedChannelName))).collect(Collectors.toList())
        );
    }


}
