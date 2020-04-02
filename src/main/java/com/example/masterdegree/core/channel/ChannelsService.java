package com.example.masterdegree.core.channel;

import com.example.masterdegree.models.dto.ChannelsGroupByCategoryResponseDto;
import com.example.masterdegree.models.model.ChannelsGroupByCategory;

import java.util.List;

public interface ChannelsService {

    List<ChannelsGroupByCategory> getAllChannelsGroupByCategoryFromDb();

    List<ChannelsGroupByCategoryResponseDto> getAllChannelsGroupByCategoryFromDbDto();

}
