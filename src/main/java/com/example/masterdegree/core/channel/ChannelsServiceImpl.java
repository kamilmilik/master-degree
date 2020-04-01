package com.example.masterdegree.core.channel;

import com.example.masterdegree.models.entity.Channel;
import com.example.masterdegree.models.entity.ChannelsGroupByCategory;
import com.example.masterdegree.repositories.ChannelsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChannelsServiceImpl implements ChannelsService {

    private Set<Channel> fetchedSelectedChannels = new HashSet<>();

    private final ChannelsRepository channelsRepository;

    @Override
    public Set<Channel> getFetchedSelectedChannels() {
        return fetchedSelectedChannels;
    }

    @Override
    public List<ChannelsGroupByCategory> getAllChannelsGroupByCategoryFromDb() {
        return channelsRepository.findAll();
    }

    @Override
    public void addFetchedChannelResourceToFetchedList(Channel channel) {
        getFetchedSelectedChannels().add(channel);
    }

    @Override
    public void removeFetchedChannelResourceFromList(Channel channel) {
        fetchedSelectedChannels.remove(channel);
    }

    @Override
    public void addAllFetchedChannelsResourceToFetchedList(Collection<Channel> channels) {
        fetchedSelectedChannels.addAll(channels);
    }

    @Override
    public void removeAllFetchedChannelsResourceToFetchedList(Collection<Channel> channels) {
        fetchedSelectedChannels.removeAll(channels);
    }

    @Override
    public boolean isAnyChannelSelected() {
        return getFetchedSelectedChannels().size() != 0;
    }



}
