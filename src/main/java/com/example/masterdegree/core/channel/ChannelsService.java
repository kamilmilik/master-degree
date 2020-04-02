package com.example.masterdegree.core.channel;

import com.example.masterdegree.models.dto.ChannelsGroupByCategoryResponseDto;
import com.example.masterdegree.models.entity.Channel;
import com.example.masterdegree.models.entity.ChannelsGroupByCategory;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ChannelsService {

    List<ChannelsGroupByCategory> getAllChannelsGroupByCategoryFromDb();

    List<ChannelsGroupByCategoryResponseDto> getAllChannelsGroupByCategoryFromDbDto();

}
