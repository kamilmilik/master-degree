package com.example.masterdegree.controllers;

import com.example.masterdegree.models.entity.Channel;
import com.example.masterdegree.services.channel.ChannelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@RepositoryRestController
@RequestMapping("/api")
public class ChannelsController {

    private ChannelsService channelsService;


    @Autowired
    public ChannelsController(ChannelsService channelsService) {
        this.channelsService = channelsService;
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
