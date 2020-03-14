package com.example.masterdegree.services.strategyfilters;

import com.example.masterdegree.models.dto.ResultTvPackage;
import com.example.masterdegree.services.channel.ChannelsService;

import java.util.List;

public class ChannelCriteriaStrategy implements CriteriaStrategy {

    private ChannelsService channelsService;

    public ChannelCriteriaStrategy(ChannelsService channelsService) {
        this.channelsService = channelsService;
    }

    @Override
    public List<ResultTvPackage> getFilteredResult(List<ResultTvPackage> resultTvPackages) {
        return channelsService.getResultBySelectedChannels(resultTvPackages);
    }
}
