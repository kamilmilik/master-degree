package com.example.masterdegree.models.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
@Document(collection = "channelsgroupbycategory")
@EqualsAndHashCode
public class ChannelsGroupByCategory {

    @Id
    @NonNull
    private final String id;
    @NonNull
    private final String categoryName;
    @NonNull
    @Getter(AccessLevel.NONE)
    private final List<Channel> channels;

    public List<Channel> getChannels() {
        return Collections.unmodifiableList(channels);
    }
}
