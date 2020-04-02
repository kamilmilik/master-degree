package com.example.masterdegree.models.mappers;

import com.example.masterdegree.models.dto.ChannelDto;
import com.example.masterdegree.models.dto.ChannelsGroupByCategoryResponseDto;
import com.example.masterdegree.models.entity.ChannelsGroupByCategory;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ChannelsGroupByCategoryMapper {

    public ChannelsGroupByCategoryResponseDto convertToDto(ChannelsGroupByCategory channelsGroupByCategory) {
        List<ChannelDto> channelDtos = channelsGroupByCategory.getChannels().stream().map(channel ->
                    new ChannelDto(channel.getName(), channel.getImgSrc())
                ).collect(Collectors.toList());
        return ChannelsGroupByCategoryResponseDto.newChannelsGroupByCategoryDto(channelsGroupByCategory.getId(), channelsGroupByCategory.getCategoryName(), channelDtos);
    }
}
