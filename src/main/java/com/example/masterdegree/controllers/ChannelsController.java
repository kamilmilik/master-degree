package com.example.masterdegree.controllers;

import com.example.masterdegree.models.ChannelsGroupByCategory;
import com.example.masterdegree.services.ChannelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/channels")
public class ChannelsController {

    private ChannelsService channelsService;

    @Autowired
    public ChannelsController(ChannelsService channelsService){
        this.channelsService = channelsService;
    }

    @GetMapping("/all")
    public List<ChannelsGroupByCategory> getAll(){
        return channelsService.findAll();
    }

    @PostMapping("/")
    public ChannelsGroupByCategory createChannel(@Valid @RequestBody ChannelsGroupByCategory channelsGroupByCategory){
        channelsService.save(channelsGroupByCategory);
        return channelsGroupByCategory;
    }
}
