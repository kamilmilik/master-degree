package com.example.masterdegree.controllers;

import com.example.masterdegree.models.dto.ChannelsGroupByCategoryResponseDto;
import com.example.masterdegree.core.channel.ChannelsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RepositoryRestController
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChannelsController {

    private final ChannelsService channelsService;

    @GetMapping("/channels")
    public List<ChannelsGroupByCategoryResponseDto> getChannelsFromDbDto() {
        return channelsService.getAllChannelsGroupByCategoryFromDbDto();
    }

}
