package com.example.masterdegree.core.channel;

import com.example.masterdegree.models.dto.ChannelsGroupByCategoryResponseDto;
import com.example.masterdegree.models.entity.Channel;
import com.example.masterdegree.models.entity.ChannelsGroupByCategory;
import com.example.masterdegree.models.mappers.ChannelsGroupByCategoryMapper;
import com.example.masterdegree.repositories.ChannelsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChannelsServiceImpl implements ChannelsService {

    private final ChannelsRepository channelsRepository;
    private final ChannelsGroupByCategoryMapper channelsGroupByCategoryMapper;

    @Override
    public List<ChannelsGroupByCategoryResponseDto> getAllChannelsGroupByCategoryFromDbDto() {
        List<ChannelsGroupByCategoryResponseDto> channelsGroupByCategoryResponseDtos = getAllChannelsGroupByCategoryFromDb().stream().map(channelsGroupByCategoryMapper::convertToDto).collect(Collectors.toList());
        return getAllChannelsGroupByCategoryFromDb().stream().map(channelsGroupByCategoryMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<ChannelsGroupByCategory> getAllChannelsGroupByCategoryFromDb() {
        return channelsRepository.findAll();
    }


}
