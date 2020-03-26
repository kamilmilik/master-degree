package com.example.masterdegree.controllers;

import com.example.masterdegree.models.entity.Channel;
import com.example.masterdegree.models.entity.ChannelsGroupByCategory;
import com.example.masterdegree.core.channel.ChannelsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

//@RepositoryRestController
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChannelsController {

    private final ChannelsService channelsService;

    @GetMapping("/channels")
    public List<ChannelsGroupByCategory> getChannels() {
        return channelsService.getAllChannelsGroupByCategoryFromDb();
    }

    @PostMapping("/channels/selected")
    @ResponseBody
    public void fetchSelectedChannel(@RequestBody Channel selectedChannel) {
        channelsService.addFetchedChannelResourceToFetchedList(selectedChannel);
        System.out.println("selected channelsy: ");
        System.out.println("Size: " + channelsService.getFetchedSelectedChannels().size());
    }

    @PostMapping("/channels/not-selected")
    @ResponseBody
    public void fetchNotSelectedChannel(@RequestBody Channel selectedChannel) {
        channelsService.removeFetchedChannelResourceFromList(selectedChannel);
        System.out.println("not selected channelsy: ");
        System.out.println("Size: " + channelsService.getFetchedSelectedChannels().size());
    }

    @PostMapping("/channels/all/selected")
    @ResponseBody
    public void fetchAllSelectedChannels(@RequestBody Collection<Channel> channels) {
        channelsService.addAllFetchedChannelsResourceToFetchedList(channels);
        System.out.println("all Size: " + channelsService.getFetchedSelectedChannels().size());
    }

    @PostMapping("/channels/all/not-selected")
    @ResponseBody
    public void fetchAllNotSelectedChannels(@RequestBody Collection<Channel> channels) {
        channelsService.removeAllFetchedChannelsResourceToFetchedList(channels);
        System.out.println("all not Size: " + channelsService.getFetchedSelectedChannels().size());
    }

}
