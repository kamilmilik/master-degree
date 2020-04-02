package com.example.masterdegree.models.dto;

import com.example.masterdegree.models.entity.Channel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChannelsGroupByCategoryResponseDto {

    @Id
    @NonNull
    private String id;
    @NonNull
    private String categoryName;
    @NonNull
    @Getter(AccessLevel.NONE)
    private List<ChannelDto> channels;

    public List<ChannelDto> getChannels() {
        return Collections.unmodifiableList(channels);
    }

    public static ChannelsGroupByCategoryResponseDto newChannelsGroupByCategoryDto(String id, String categoryName, List<ChannelDto> channels){
        return new ChannelsGroupByCategoryResponseDto(id, categoryName, channels);
    }
}