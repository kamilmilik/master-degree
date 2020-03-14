package com.example.masterdegree.services.channel;

import com.example.masterdegree.models.dto.ResultTvPackage;
import com.example.masterdegree.models.entity.Channel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ChannelsService {

    void addFetchedChannelResourceToFetchedList(Channel channel);

    void removeFetchedChannelResourceFromList(Channel channel);

    Set<Channel> getFetchedSelectedChannels();

    boolean isAnyChannelSelected();

    List<ResultTvPackage> getResultBySelectedChannels(List<ResultTvPackage> resultTvPackages);

    void addAllFetchedChannelsResourceToFetchedList(Collection<Channel> channels);

    void removeAllFetchedChannelsResourceToFetchedList(Collection<Channel> channels);
}
