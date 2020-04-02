package com.example.masterdegree.models.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@Document(collection = "channelsgroupbycategory")
public class ChannelsGroupByCategory {

    @Id
    @NonNull
    @Getter
    private String id;
    @NonNull
    @Getter
    private String categoryName;
    @NonNull
    @Getter
    private List<Channel> channels;

}
