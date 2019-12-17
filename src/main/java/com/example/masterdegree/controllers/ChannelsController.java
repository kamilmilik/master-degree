package com.example.masterdegree.controllers;

import com.example.masterdegree.models.Channel;
import com.example.masterdegree.models.ChannelObject;
import com.example.masterdegree.repositories.ChannelsRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/channels")
public class ChannelsController {

    private ChannelsRepository channelsRepository;

    public ChannelsController(ChannelsRepository channelsRepository) {
        this.channelsRepository = channelsRepository;
    }

    @GetMapping("/all")
    public List<ChannelObject> getAll(){
        return channelsRepository.findAll();
    }

    @PostMapping("/")
    public ChannelObject createChannel(@Valid @RequestBody ChannelObject channelObject){
        channelsRepository.save(channelObject);
        return channelObject;
    }
}
