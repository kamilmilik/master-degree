package com.example.masterdegree.services.channel;

import com.example.masterdegree.models.dto.ResultTvPackage;
import com.example.masterdegree.models.entity.Channel;
import com.example.masterdegree.models.entity.TvPackage;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChannelsServiceImpl implements ChannelsService {

    private Set<Channel> fetchedSelectedChannels;

    public ChannelsServiceImpl() {
        this.fetchedSelectedChannels = new HashSet<>();
    }

    @Override
    public Set<Channel> getFetchedSelectedChannels() {
        return fetchedSelectedChannels;
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

    // TODO KM sprawdzic czy dziala w wiekszej ilosci danych
    // TODO KM refactor
    @Override
    public List<ResultTvPackage> getResultBySelectedChannels(List<ResultTvPackage> resultTvPackages) {
        for (Channel selectedChannel : getFetchedSelectedChannels()) {
            for (Iterator<ResultTvPackage> it = resultTvPackages.iterator(); it.hasNext(); ) {
                ResultTvPackage resultTvPackage = it.next();
                boolean isFoundSelectedChannel = false;
                for (Channel channel : resultTvPackage.getFilteredTvPackage().getChannels()) {
                    if (channel.equals(selectedChannel)) {
                        isFoundSelectedChannel = true; // znalazlem kanal w main tv package
                        break;
                    }
                }
                if (!isFoundSelectedChannel) { // nie znalazlem kanalu w main tv package, moze jest w dodatkowych
                    for (TvPackage extraAvailableTvPackage : resultTvPackage.getFilteredTvPackage().getExtraAvailableTvPackages()) {
                        for (Channel channel : extraAvailableTvPackage.getChannels())
                            if (channel.equals(selectedChannel)) {
                                isFoundSelectedChannel = true; // jest w dodatkowych, nie w main
                                resultTvPackage.getFilteredTvPackage().getExtraTvPackagesWhichMeetCriteria().add(extraAvailableTvPackage);
                                break;
                            }
                    }
                    if (!isFoundSelectedChannel) { // nie ma w tym main, ani w dodatkowych, usun ten proponowany pakiet glowny z dodatkowymi
                        it.remove();
                    }
                }
            }
        }
        return resultTvPackages;
    }


}
