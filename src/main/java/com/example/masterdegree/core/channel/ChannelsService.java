package com.example.masterdegree.core.channel;

import com.example.masterdegree.models.entity.Channel;
import com.example.masterdegree.models.entity.ChannelsGroupByCategory;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ChannelsService {

    List<ChannelsGroupByCategory> getAllChannelsGroupByCategoryFromDb();

    void addFetchedChannelResourceToFetchedList(Channel channel);

    void removeFetchedChannelResourceFromList(Channel channel);

    Set<Channel> getFetchedSelectedChannels();

    boolean isAnyChannelSelected();

    void addAllFetchedChannelsResourceToFetchedList(Collection<Channel> channels);

    void removeAllFetchedChannelsResourceToFetchedList(Collection<Channel> channels);
}
