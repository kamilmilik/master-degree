package com.example.masterdegree.core.channel;

import com.example.masterdegree.models.dto.ChannelsGroupByCategoryResponseDto;
import com.example.masterdegree.models.mappers.ChannelsGroupByCategoryMapper;
import com.example.masterdegree.models.model.ChannelsGroupByCategory;
import com.example.masterdegree.repositories.ChannelsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChannelsService {

    private final ChannelsRepository channelsRepository;
    private final ChannelsGroupByCategoryMapper channelsGroupByCategoryMapper;

    public List<ChannelsGroupByCategoryResponseDto> getAllChannelsGroupByCategoryFromDbDto() {
        return getAllChannelsGroupByCategoryFromDb().stream().map(channelsGroupByCategoryMapper::convertToDto).collect(Collectors.toList());
    }

    private List<ChannelsGroupByCategory> getAllChannelsGroupByCategoryFromDb() {
        return channelsRepository.findAll();
    }

}
