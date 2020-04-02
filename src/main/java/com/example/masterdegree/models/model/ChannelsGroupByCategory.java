package com.example.masterdegree.models.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
@Document(collection = "channelsgroupbycategory")
public class ChannelsGroupByCategory {

    @Id
    @NonNull
    private String id;
    @NonNull
    private String categoryName;
    @NonNull
    @Getter(AccessLevel.NONE)
    private List<Channel> channels;

    public List<Channel> getChannels() {
        return Collections.unmodifiableList(channels);
    }
}
