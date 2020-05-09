package com.example.masterdegree.core.strategyfilters;

import com.example.masterdegree.models.model.Channel;
import com.example.masterdegree.models.model.Criteria;
import com.example.masterdegree.models.model.TvPackage;
import com.example.masterdegree.models.model.filter.ResultTvPackage;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
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
        for (Channel selectedChannel : criteria.getChannels()) {
            resultTvPackages.removeIf(resultTvPackage -> {
                boolean isFoundSelectedChannel = searchChannelInMainTvPackage(resultTvPackage, selectedChannel);
                if (!isFoundSelectedChannel) {
                    isFoundSelectedChannel = searchChannelInExtraAvailablePackages(resultTvPackage, selectedChannel);
                }
                return !isFoundSelectedChannel;
            });
        }
        return resultTvPackages;
    }

    boolean searchChannelInMainTvPackage(ResultTvPackage resultTvPackage, Channel searchedChannel) {
        return resultTvPackage.getFilteredTvPackage().getChannels().stream().anyMatch(channel -> channel.isTheSame(searchedChannel));
    }

    private boolean searchChannelInExtraAvailablePackages(ResultTvPackage resultTvPackage, Channel searchedChannel) {
        return resultTvPackage.getFilteredTvPackage().getExtraTvPackagesWhichMeetCriteria().addAll(
                resultTvPackage.getFilteredTvPackage().getExtraAvailableTvPackages().stream()
                        .filter(tvPackage -> tvPackage.getChannels().stream()
                                .anyMatch(channel -> channel.isTheSame(searchedChannel))).collect(Collectors.toList())
        );
    }


}
